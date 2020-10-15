package rest.trader.traderapi.entity.Asset;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import rest.trader.traderapi.entity.Commodity.Commodity;
import rest.trader.traderapi.entity.Company.Company;

import java.util.UUID;

@Entity
@Data
public class Asset {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name="commodity_id")
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @Column
    private Integer count;
}
