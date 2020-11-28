package rest.trader.traderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Object Not Found!")
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String msg, UUID uuid) {
        super("404: Error - " + msg + uuid.toString() + "'.");
    }

    public ObjectNotFoundException(String msg, String name) {
        super("404: Error - " + msg + name + "'.");
    }

    public ObjectNotFoundException(String msg) {
        super("404: Error - " + msg);
    }
}