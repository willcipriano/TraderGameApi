package rest.trader.traderapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderapi.entity.Universe;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UniverseRepository extends CrudRepository<Universe, String> {
    public List<Universe> findAllByCreatedBeforeOrderByCreated(LocalDateTime created);
}
