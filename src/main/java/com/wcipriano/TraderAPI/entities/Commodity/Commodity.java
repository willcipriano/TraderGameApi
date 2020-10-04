package com.wcipriano.TraderAPI.entities.Commodity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Commodity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private CommodityClass commodityClass;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private CommodityUnit unit;

}
