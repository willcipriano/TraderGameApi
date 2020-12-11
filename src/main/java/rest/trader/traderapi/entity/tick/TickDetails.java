package rest.trader.traderapi.entity.tick;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TickDetails {
    private Integer tickDurationSeconds;
    private Integer prevTickDurationSeconds;
    private UUID prevUUID;
    private UUID nextUUID;
}
