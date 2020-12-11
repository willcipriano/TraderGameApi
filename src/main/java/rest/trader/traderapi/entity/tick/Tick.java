package rest.trader.traderapi.entity.tick;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tick {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private Boolean completed;

    @Column
    private Boolean active;

    @Column
    private Boolean locked;

    @Column(name = "tick")
    private BigInteger count;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json")
    private TickDetails details;
}
