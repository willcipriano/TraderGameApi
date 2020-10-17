package rest.trader.traderapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderapi.entity.exchange.Exchange;

import java.util.UUID;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange, UUID> {
}
