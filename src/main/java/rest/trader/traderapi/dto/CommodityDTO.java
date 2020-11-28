package rest.trader.traderapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommodityDTO extends UniverseObjectDTO {
    private String dtoClass = "commodity";

    @ApiModelProperty(notes = "Every Commodity has a unique UUID.")
    private UUID uuid;

    @ApiModelProperty(notes = "Name for the Commodity.")
    private String name;

    @ApiModelProperty(notes = "Short description of the Commodity.")
    private String description;
}
