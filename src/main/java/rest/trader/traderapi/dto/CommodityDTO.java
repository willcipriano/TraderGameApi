package rest.trader.traderapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommodityDTO {
    private String dtoClass = "commodity";
    private UUID uuid;
    private UUID commodityTypeUUID;
    private String name;
    private String description;
    private LocalDateTime lastFetched;
}
