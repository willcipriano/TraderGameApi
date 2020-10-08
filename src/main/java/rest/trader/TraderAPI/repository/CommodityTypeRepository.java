package rest.trader.TraderAPI.repository;

import rest.trader.TraderAPI.entities.Commodity.CommodityType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommodityTypeRepository extends CrudRepository<CommodityType, UUID> {
}
