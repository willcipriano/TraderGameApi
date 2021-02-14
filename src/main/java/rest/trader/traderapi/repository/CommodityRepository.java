package rest.trader.traderapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import rest.trader.traderapi.entity.commodity.Commodity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderapi.entity.commodity.CommodityType;

import java.util.UUID;

@Repository
public interface CommodityRepository extends CrudRepository<Commodity, UUID>,
        PagingAndSortingRepository<Commodity, UUID>, JpaSpecificationExecutor<Commodity> {
    Commodity findByName(String name);

    Commodity findByDescription(String description);

    Commodity findByUuid(UUID uuid);

    Page<Commodity> findCommoditiesByType(CommodityType commodityType, Pageable pageable);
}
