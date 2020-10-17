package rest.trader.traderapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderapi.entity.ledger.LedgerEntry;

import java.util.UUID;

@Repository
public interface LedgerEntryRepository extends CrudRepository<LedgerEntry, UUID> {
}
