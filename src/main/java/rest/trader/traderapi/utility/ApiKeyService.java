package rest.trader.traderapi.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import rest.trader.traderapi.enumerator.ApiKeyEnum;
import rest.trader.traderapi.enumerator.ApiKeyRequestorEnum;
import rest.trader.traderapi.exception.ApiKeyNotSetException;

@Service
@Slf4j
public class ApiKeyService {
    private final String undefinedKeyStubLower = "apikeygoeshere";

    // defined api keys
    private final String secretAlphaApiKey = "ALPHAAPIKEYGOESHERE";

    public String reveal(ApiKeyEnum apiKeyEnum, ApiKeyRequestorEnum apiKeyRequestorEnum) {
        log.trace("Request to reveal Api Key: '{}', for '{}'.", apiKeyEnum.toString(), apiKeyRequestorEnum.toString());
        return getApiKey(apiKeyEnum, apiKeyRequestorEnum);
    }

    public String getApiKey(ApiKeyEnum apiKeyEnum, ApiKeyRequestorEnum apiKeyRequestorEnum) {

        if (apiKeyEnum.equals(ApiKeyEnum.UNDEFINED)) {
            log.error("Attempt to find undefined API key.", new ApiKeyNotSetException("Undefined Value, Enum not set!"));
            return null;
        }

        String secretKey;

        switch (apiKeyEnum) {
            case ALPHA:
                secretKey = secretAlphaApiKey;
                break;

            default:
                log.error("Attempt to find unimplemented API key.", new NotImplementedException("Case not implemented for ".concat(apiKeyEnum.toString())));
                return null;

        }

        if (secretKey.toLowerCase().equals(apiKeyEnum.toShortAndLower() + undefinedKeyStubLower) || secretKey.equals("")) {
            log.error("Failure to retrieve api key '{}', for '{}'.", apiKeyEnum.toString(), apiKeyRequestorEnum.toString(), new ApiKeyNotSetException(apiKeyEnum.toShortAndLower().toUpperCase()));
            return null;
        }

        return secretKey;
    }
}
