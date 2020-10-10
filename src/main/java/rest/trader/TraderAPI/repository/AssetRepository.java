package rest.trader.TraderAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.TraderAPI.entity.Asset.Asset;

import java.util.UUID;

@Repository
public interface AssetRepository extends CrudRepository<Asset, UUID> {
}
