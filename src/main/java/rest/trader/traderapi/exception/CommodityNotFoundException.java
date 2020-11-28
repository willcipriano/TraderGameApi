package rest.trader.traderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Commodity Not Found!")
public class CommodityNotFoundException extends ObjectNotFoundException {
    public CommodityNotFoundException(String msg, UUID uuid) {
        super(msg, uuid);
    }

    public CommodityNotFoundException(String msg, String name) {
        super(msg, name);
    }
}
