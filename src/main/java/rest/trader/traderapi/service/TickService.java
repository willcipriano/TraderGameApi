package rest.trader.traderapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rest.trader.traderapi.entity.Universe;
import rest.trader.traderapi.entity.tick.Tick;
import rest.trader.traderapi.entity.tick.TickDetails;
import rest.trader.traderapi.exception.TickNotFoundException;
import rest.trader.traderapi.exception.UniverseNotFoundException;
import rest.trader.traderapi.repository.TickRepository;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
@Slf4j
public class TickService {

    TickRepository repository;
    UniverseService universeService;
    Universe universe;

    @Autowired
    public TickService(TickRepository tickRepository, UniverseService universeService) {
        this.repository = tickRepository;
        this.universeService = universeService;
        this.universe = null;
    }

    public void save(Tick tick) {
        repository.save(tick);
    }

    public TickRepository getRepository() {
        return this.repository;
    }

    public Tick getLatestTick() {
        Tick tick = repository.findFirstByOrderByStartTimeDesc();
        if (tick != null) {
            return tick;
        }
        throw new TickNotFoundException("Unable to find a tick!");
    }

    public Tick createFirstTick() {
        TickDetails tickDetails = TickDetails.builder().build();

        return Tick.builder().active(true).count(BigInteger.ONE).completed(false).locked(false).details(tickDetails)
                .startTime(LocalDateTime.now()).build();
    }

    public Tick createNewTick(BigInteger count) {
        TickDetails tickDetails = TickDetails.builder().build();

        return Tick.builder().active(true).count(count.add(BigInteger.ONE)).completed(false).locked(false)
                .details(tickDetails).startTime(LocalDateTime.now()).build();

    }

    @Scheduled(cron = "1 * * * * *")
    public void cronJobTick() {
        Tick latestTick;

        if (universe == null) {
            try {
                universe = universeService.getUniverse();
            } catch (UniverseNotFoundException ex) {
                log.error("Unable to progress simulation, universe not yet instantiated.");
                throw ex;
            }
        }

        try {
            latestTick = getLatestTick();
        } catch (TickNotFoundException ex) {
            latestTick = createFirstTick();
        }

        if (Boolean.TRUE.equals(latestTick.getLocked())) {
            log.error("Tick is locked.. aborting.");
            return;
        }

        latestTick.setEndTime(LocalDateTime.now());
        latestTick.setCompleted(true);
        latestTick.setLocked(false);
        Integer durationSeconds = (int) Duration.between(latestTick.getStartTime(), latestTick.getEndTime())
                .toSeconds();
        latestTick.getDetails().setTickDurationSeconds(durationSeconds);

        Tick newTick = createNewTick(latestTick.getCount());
        newTick.getDetails().setPrevTickDurationSeconds(durationSeconds);
        newTick.getDetails().setPrevUUID(latestTick.getUuid());
        latestTick.setActive(false);
        newTick.setActive(true);

        repository.save(newTick);

        latestTick.getDetails().setNextUUID(newTick.getUuid());
        repository.save(latestTick);

        log.info("New Tick: [" + newTick.getCount().toString() + "] - <" + newTick.getUuid().toString()
                + "> | Previous Tick Lasted: " + durationSeconds.toString() + " seconds.");
    }

}
