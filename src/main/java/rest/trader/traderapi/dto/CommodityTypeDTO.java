package rest.trader.traderapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommodityTypeDTO extends UniverseObjectDTO {
    private String dtoClass = "commodityType";

    @ApiModelProperty(notes = "Every CommodityType has a unique UUID.")
    private UUID uuid;

    @ApiModelProperty(notes = "The name of the CommodityType.")
    private String name;

    @ApiModelProperty(notes = "A short description of the CommodityType.")
    private String description;
}
