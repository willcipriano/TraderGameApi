package rest.trader.traderAPI.entity.Ledger;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import rest.trader.traderAPI.entity.Company.Company;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class LedgerEntry {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;

    @Column
    private LedgerEntryType type;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "ledger_id")
    private Ledger ledger;

    @Column
    private Integer amount;
}
