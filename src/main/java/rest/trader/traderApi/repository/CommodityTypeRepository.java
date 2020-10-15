package rest.trader.traderApi.repository;

import rest.trader.traderApi.entity.Commodity.CommodityType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommodityTypeRepository extends CrudRepository<CommodityType, UUID> {
}
