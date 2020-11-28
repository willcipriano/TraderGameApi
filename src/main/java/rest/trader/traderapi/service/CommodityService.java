package rest.trader.traderapi.service;

import com.remondis.remap.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rest.trader.traderapi.dto.CommodityDTO;
import rest.trader.traderapi.entity.commodity.Commodity;
import rest.trader.traderapi.entity.commodity.CommodityType;
import rest.trader.traderapi.exception.CommodityNotFoundException;
import rest.trader.traderapi.repository.CommodityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommodityService {

    CommodityRepository repository;
    CommodityTypeService commodityTypeService;
    Mapper<Commodity, CommodityDTO> toDtoMapper;

    @Autowired
    public CommodityService(CommodityRepository commodityRepository, Mapper<Commodity, CommodityDTO> toDtoMapper,
            CommodityTypeService commodityTypeService) {
        this.repository = commodityRepository;
        this.toDtoMapper = toDtoMapper;
        this.commodityTypeService = commodityTypeService;
    }

    public Page<Commodity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Commodity getCommodityByUUID(UUID uuid) throws CommodityNotFoundException {
        Commodity result = repository.findByUuid(uuid);
        if (result != null) {
            return result;
        }
        throw new CommodityNotFoundException("Unable to locate commodity by UUID: '", uuid);
    }

    public Commodity getCommodityByName(String name) throws CommodityNotFoundException {
        Commodity result = repository.findByName(name);
        if (result != null) {
            return result;
        }
        throw new CommodityNotFoundException("Unable to locate commodity by name: '", name);
    }

    public Commodity getExampleCommodity() {
        Commodity result = new Commodity();
        CommodityType resultType = new CommodityType();

        resultType.setDescription("quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        resultType.setName("culpa qui officia");
        resultType.setUuid(UUID.fromString("357e7dac-bf96-4be6-9d30-1582ac4c34d4"));

        result.setType(resultType);
        result.setDescription(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        result.setName("Lorem ipsum");
        result.setUuid(UUID.fromString("776311fd-0a98-41c3-896f-83d674fe10ed"));

        return result;
    }

    public Page<Commodity> getCommoditiesByTypeUuid(UUID uuid, Pageable pageable) {
        CommodityType commodityType = commodityTypeService.getByUuid(uuid);
        return repository.findCommoditiesByType(commodityType, pageable);
    }

    public CommodityDTO toCommodityDTO(Commodity commodity) {
        return toDtoMapper.map(commodity);
    }

    public List<CommodityDTO> toCommodityDtos(Page<Commodity> commodities) {
        List<CommodityDTO> results = new ArrayList<>();

        for (Commodity commodity : commodities) {
            results.add(toDtoMapper.map(commodity));
        }

        return results;
    }

    public void saveOrUpdate(Commodity commodity) {
        Commodity result = repository.findByName(commodity.getName());
        if (result == null) {
            commodityTypeService.saveOrUpdate(commodity.getType());
            repository.save(commodity);
        } else {
            if (!result.getType().getName().equals(commodity.getType().getName())) {
                commodityTypeService.saveOrUpdate(commodity.getType());
                result.setType(commodity.getType());
            }
            result.setName(commodity.getName());
            result.setDescription(commodity.getDescription());
            repository.save(result);
        }
    }

    public CommodityRepository getRepository() {
        return this.repository;
    }
}
