package rest.trader.traderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Universe Not Found!")
public class UniverseNotFoundException extends ObjectNotFoundException {
    public UniverseNotFoundException(String msg, UUID uuid) {
        super(msg, uuid);
    }

    public UniverseNotFoundException(String msg, String name) {
        super(msg, name);
    }

    public UniverseNotFoundException(String msg) {
        super(msg);
    };
}
