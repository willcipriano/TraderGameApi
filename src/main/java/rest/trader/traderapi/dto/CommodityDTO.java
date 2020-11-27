package rest.trader.traderapi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CommodityDTO {
    private String dtoClass = "commodity";
    private UUID uuid;
    private UUID typeUUID;
    private String name;
    private String description;
}
