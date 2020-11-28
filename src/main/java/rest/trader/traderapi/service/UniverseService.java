package rest.trader.traderapi.service;

import org.springframework.stereotype.Service;
import rest.trader.traderapi.dto.dev.UniverseSeedDTO;
import rest.trader.traderapi.entity.Universe;
import rest.trader.traderapi.exception.UniverseNotFoundException;
import rest.trader.traderapi.repository.UniverseRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UniverseService {

    UniverseRepository repository;
    Universe universe = null;

    public UniverseService(UniverseRepository universeRepository) {
        this.repository = universeRepository;
    }

    public Universe createAndSaveUniverse(String name, UniverseSeedDTO universeSeed) {
        Universe universe = Universe.builder().name(name).universeSeed(universeSeed).created(LocalDateTime.now())
                .build();

        save(universe);
        return universe;
    }

    public void save(Universe universe) {
        repository.save(universe);
    }

    public Universe getUniverse() {
        if (universe == null) {

            List<Universe> universeList = repository.findAllByCreatedBeforeOrderByCreated(LocalDateTime.now());

            if (universeList.size() <= 0) {
                throw new UniverseNotFoundException("Unable to locate a universe.");
            }

            universe = universeList.get(0);
        }

        return universe;
    }
}
