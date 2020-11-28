package rest.trader.traderapi.service;

import org.springframework.stereotype.Service;
import rest.trader.traderapi.entity.commodity.CommodityType;
import rest.trader.traderapi.exception.CommodityTypeNotFoundException;
import rest.trader.traderapi.repository.CommodityTypeRepository;

import java.util.UUID;

@Service
public class CommodityTypeService {

    CommodityTypeRepository repository;

    public CommodityTypeService(CommodityTypeRepository commodityTypeRepository) {
        repository = commodityTypeRepository;
    }

    public CommodityType getByUuid(UUID uuid) {
        CommodityType commodityType = repository.findByUuid(uuid);
        if (commodityType != null) {
            return commodityType;
        }
        throw new CommodityTypeNotFoundException("Unable to find CommodityType by UUID: '", uuid);
    }

    public CommodityType saveOrUpdate(CommodityType commodityType) {
        CommodityType result = repository.findByName(commodityType.getName());
        if (result == null) {
            repository.save(commodityType);
            return commodityType;
        } else {
            result.setDescription(commodityType.getDescription());
            result.setName(commodityType.getName());
            repository.save(result);
            return result;
        }
    }

    public CommodityTypeRepository getRepository() {
        return this.repository;
    }

}
