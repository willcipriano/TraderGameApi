package rest.trader.traderApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderApi.entity.Asset.Asset;

import java.util.UUID;

@Repository
public interface AssetRepository extends CrudRepository<Asset, UUID> {
}
