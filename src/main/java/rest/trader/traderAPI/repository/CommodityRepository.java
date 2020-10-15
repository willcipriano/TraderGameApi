package rest.trader.traderAPI.repository;

import rest.trader.traderAPI.entity.Commodity.Commodity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommodityRepository extends CrudRepository<Commodity, UUID> {
}
