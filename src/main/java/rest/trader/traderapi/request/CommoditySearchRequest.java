package rest.trader.traderapi.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommoditySearchRequest {
    String name;

    String description;

    String typeName;
}
