package rest.trader.traderAPI.repository;

import rest.trader.traderAPI.entity.Commodity.Commodity;
import rest.trader.traderAPI.entity.Commodity.CommodityType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommodityRepositoryTest {

    @Autowired
    CommodityRepository commodityRepository;

    @Test
    @Transactional
    void saveTest() {
        CommodityType saveMeType = new CommodityType();
        saveMeType.setName("fake type");
        saveMeType.setDescription("fake description");

        Commodity saveMe = new Commodity();
        saveMe.setName("fake name");
        saveMe.setDescription("fake description");

        commodityRepository.save(saveMe);
        assertThat(saveMe.getName()).isEqualTo("fake name");
    }
}
