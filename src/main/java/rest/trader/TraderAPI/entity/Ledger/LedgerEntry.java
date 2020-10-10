package rest.trader.TraderAPI.entity.Ledger;

import org.hibernate.annotations.GenericGenerator;
import rest.trader.TraderAPI.entity.Company.Company;

import javax.persistence.*;
import java.util.UUID;

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
