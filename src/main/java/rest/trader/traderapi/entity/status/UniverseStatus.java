package rest.trader.traderapi.entity.status;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UniverseStatus {

    String name;

    LocalDateTime lightLetBe;

    TickStatus tick;

    LocalDateTime updated;

}
