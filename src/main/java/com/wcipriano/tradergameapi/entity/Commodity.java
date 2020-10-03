package com.wcipriano.tradergameapi.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Commodity {

    private UUID id;

    private String name;

    private CommodityType type;

}
