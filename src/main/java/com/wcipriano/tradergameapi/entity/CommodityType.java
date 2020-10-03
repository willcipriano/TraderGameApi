package com.wcipriano.tradergameapi.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class CommodityType {

    private UUID id;

    private String name;

    private String description;
}
