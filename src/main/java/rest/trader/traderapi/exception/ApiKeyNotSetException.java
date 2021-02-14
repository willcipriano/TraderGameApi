package rest.trader.traderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FAILED_DEPENDENCY, reason = "API key not set!")
public class ApiKeyNotSetException extends RuntimeException {

    public ApiKeyNotSetException(String keyType) {
        super(String.format("Unable to find for %s", keyType));
    }
}
