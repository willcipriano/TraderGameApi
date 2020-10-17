package rest.trader.traderapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class AssetRepositoryTest {

    @Autowired
    AssetRepository assetRepository;

    @Test
    @Transactional
    void saveTest() {
    }
}
