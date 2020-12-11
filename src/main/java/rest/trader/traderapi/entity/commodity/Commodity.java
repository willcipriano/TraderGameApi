package rest.trader.traderapi.entity.commodity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Commodity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id")
    private CommodityType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}
