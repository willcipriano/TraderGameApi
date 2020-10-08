package rest.trader.TraderAPI.repository;

import rest.trader.TraderAPI.entities.Commodity.Commodity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommodityRepository extends CrudRepository<Commodity, UUID> {
}
