package rest.trader.traderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Tick Not Found!")
public class TickNotFoundException extends ObjectNotFoundException {
    public TickNotFoundException(String msg, UUID uuid) {
        super(msg, uuid);
    }

    public TickNotFoundException(String msg, String name) {
        super(msg, name);
    }

    public TickNotFoundException(String msg) {
        super(msg);
    };
}
