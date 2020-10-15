package rest.trader.traderapi.entity.Ledger;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import rest.trader.traderapi.entity.Company.Company;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Ledger {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @Column
    private Integer balance;
}