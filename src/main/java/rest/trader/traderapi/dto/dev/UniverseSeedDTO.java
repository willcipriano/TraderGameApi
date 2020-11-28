package rest.trader.traderapi.dto.dev;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import rest.trader.traderapi.dto.UniverseObjectDTO;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UniverseSeedDTO extends UniverseObjectDTO {
    @ApiModelProperty(example = "My Universe", required = true, accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private String name;

    @ApiModelProperty(example = "universeSeed", readOnly = true, accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String dtoClass = "universeSeed";

    @ApiModelProperty(example = "false", readOnly = true, accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Boolean completed = false;

    @ApiModelProperty(example = "false", readOnly = true, accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private Boolean skipMinimumCommodities = false;

    @ApiModelProperty(example = "false", readOnly = true, accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Boolean minimumCommoditiesCompletedSuccessfully = false;

    public UniverseSeedDTO(UniverseSeedDTO anotherUniverseSeedDTO) {
        this.dtoClass = anotherUniverseSeedDTO.getDtoClass();
        this.name = anotherUniverseSeedDTO.getName();
        this.completed = anotherUniverseSeedDTO.getCompleted();
        this.minimumCommoditiesCompletedSuccessfully = anotherUniverseSeedDTO
                .getMinimumCommoditiesCompletedSuccessfully();
        this.skipMinimumCommodities = anotherUniverseSeedDTO.getSkipMinimumCommodities();
    }
}
