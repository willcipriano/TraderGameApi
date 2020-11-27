package rest.trader.traderapi.service;

import com.remondis.remap.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;
import rest.trader.traderapi.dto.CommodityDTO;
import rest.trader.traderapi.entity.commodity.Commodity;
import rest.trader.traderapi.entity.commodity.CommodityType;
import rest.trader.traderapi.exception.CommodityNotFoundException;
import rest.trader.traderapi.repository.CommodityRepository;
import springfox.documentation.swagger2.mappers.ModelMapper;
import springfox.documentation.swagger2.mappers.ModelSpecificationMapper;

import java.util.UUID;

@Service
public class CommodityService {

    CommodityRepository repository;
    Mapper<Commodity, CommodityDTO> toDtoMapper;

    @Autowired
    public CommodityService(CommodityRepository commodityRepository, Mapper<Commodity, CommodityDTO> toDtoMapper) {
        this.repository = commodityRepository;
        this.toDtoMapper = toDtoMapper;
    }

    public Commodity getCommodityByUUID(UUID uuid) throws CommodityNotFoundException {
        Commodity result = repository.findByUuid(uuid);
        if (result != null) {
            return result;
        }
        throw new CommodityNotFoundException();
    }

    public Commodity getCommodityByName(String name) throws CommodityNotFoundException {
        Commodity result = repository.findByName(name);
        if (result != null) {
            return result;
        }
        throw new CommodityNotFoundException();
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

    public CommodityDTO toCommodityDTO(Commodity commodity) {
        return toDtoMapper.map(commodity);
    }
}
