package rest.trader.traderapi.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.trader.traderapi.entity.Universe;
import rest.trader.traderapi.entity.status.TickStatus;
import rest.trader.traderapi.entity.status.UniverseStatus;
import rest.trader.traderapi.entity.tick.Tick;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UniverseStatusService {

    private final UniverseService universeService;
    private final TickService tickService;

    private UniverseStatus currentStatus;

    @Autowired
    public UniverseStatusService(UniverseService universeService, TickService tickService) {
        this.universeService = universeService;
        this.tickService = tickService;
    }

    public UniverseStatus getUniverseStatus() {
        LocalDateTime now = LocalDateTime.now();

        if (currentStatus == null || currentStatus.getUpdated().isAfter(now.plusSeconds(90))) {
            log.info("Generating new universe status");

            Universe universe = universeService.getUniverse();
            Tick tick = tickService.getLatestTick();

            currentStatus = UniverseStatus.builder().name(universe.getUniverseSeed().getName())
                    .lightLetBe(universe.getCreated()).tick(TickStatus.builder().tick(tick.getCount())
                            .startTime(tick.getStartTime()).uuid(tick.getUuid()).build())
                    .updated(now).build();

            return currentStatus;

        }
        return currentStatus;
    }
}
