package rest.trader.traderapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderapi.entity.ledger.Ledger;

import java.util.UUID;

@Repository
public interface LedgerRepository extends CrudRepository<Ledger, UUID> {
}
