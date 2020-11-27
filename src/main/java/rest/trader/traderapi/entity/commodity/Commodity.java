package rest.trader.traderapi.entity.commodity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Commodity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CommodityType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    public UUID getCommodityTypeUUID() {
        if (Objects.nonNull(this.type)) {
            return this.type.getUuid();
        }
        return null;
    }
}
