package rest.trader.traderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "CommodityType Not Found!")
public class CommodityTypeNotFoundException extends ObjectNotFoundException {
    public CommodityTypeNotFoundException(String msg, UUID uuid) {
        super(msg, uuid);
    }

    public CommodityTypeNotFoundException(String msg, String name) {
        super(msg, name);
    }
}
