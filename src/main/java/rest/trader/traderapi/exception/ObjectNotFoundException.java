package rest.trader.traderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Object Not Found!")
public class ObjectNotFoundException extends RuntimeException {
    private static final String MSG_HEADER = "404: Error - ";

    public ObjectNotFoundException(String msg, UUID uuid) {
        super(MSG_HEADER + msg + uuid.toString() + "'.");
    }

    public ObjectNotFoundException(String msg, String name) {
        super(MSG_HEADER + msg + name + "'.");
    }

    public ObjectNotFoundException(String msg) {
        super(MSG_HEADER + msg);
    }
}