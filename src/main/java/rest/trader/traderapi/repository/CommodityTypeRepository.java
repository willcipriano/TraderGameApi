package rest.trader.traderapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rest.trader.traderapi.entity.commodity.CommodityType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommodityTypeRepository
        extends CrudRepository<CommodityType, UUID>, PagingAndSortingRepository<CommodityType, UUID> {
    CommodityType findByName(String name);

    CommodityType findByUuid(UUID uuid);
}
