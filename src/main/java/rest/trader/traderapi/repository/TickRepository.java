package rest.trader.traderapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderapi.entity.tick.Tick;

import java.util.UUID;

@Repository
public interface TickRepository extends CrudRepository<Tick, UUID>, PagingAndSortingRepository<Tick, UUID> {

    Tick findFirstByOrderByStartTimeDesc();

}
