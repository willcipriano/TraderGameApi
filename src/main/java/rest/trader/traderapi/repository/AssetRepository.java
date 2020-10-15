package rest.trader.traderapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderapi.entity.Asset.Asset;

import java.util.UUID;

@Repository
public interface AssetRepository extends CrudRepository<Asset, UUID> {
}
