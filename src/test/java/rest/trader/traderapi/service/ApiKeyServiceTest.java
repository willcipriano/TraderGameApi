package rest.trader.traderapi.service;

import org.junit.Assume;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rest.trader.traderapi.BaseTraderTest;

import rest.trader.traderapi.enumerator.ApiKeyEnum;
import rest.trader.traderapi.enumerator.ApiKeyRequestorEnum;
import rest.trader.traderapi.exception.ApiKeyNotSetException;
import rest.trader.traderapi.utility.ApiKeyService;

@SpringBootTest
class ApiKeyServiceTest extends BaseTraderTest {

    @Autowired
    ApiKeyService apiKeyService;

    @Test
    void testDockerKeyReplacementSoftTest() {
        try {
            String apiKey = apiKeyService.reveal(ApiKeyEnum.ALPHA, ApiKeyRequestorEnum.TEST);
        } catch (ApiKeyNotSetException ex) {
            Assume.assumeNoException("API key test threw an exception, api keys likely have not been injected", ex);
        }

    }
}
