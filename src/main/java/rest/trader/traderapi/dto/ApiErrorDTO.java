package rest.trader.traderapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorDTO extends UniverseObjectDTO {
    private String dtoClass = "apiError";
    private String dtoClassName;
    private String error;
    private LocalDateTime updated;
}
