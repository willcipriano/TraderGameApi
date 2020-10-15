package rest.trader.traderAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderAPI.entity.Asset.Asset;

import java.util.UUID;

@Repository
public interface AssetRepository extends CrudRepository<Asset, UUID> {
}
