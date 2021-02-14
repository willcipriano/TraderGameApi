package rest.trader.traderapi.entity.status;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TickStatus {
    UUID uuid;

    LocalDateTime startTime;

    BigInteger tick;

}
