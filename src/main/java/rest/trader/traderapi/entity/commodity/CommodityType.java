package rest.trader.traderapi.entity.commodity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommodityType {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @Column
    private String name;

    @Column
    private String description;
}
