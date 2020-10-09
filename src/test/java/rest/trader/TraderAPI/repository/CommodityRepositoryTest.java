package rest.trader.TraderAPI.repository;

import rest.trader.TraderAPI.entity.Commodity.Commodity;
import rest.trader.TraderAPI.entity.Commodity.CommodityType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class CommodityRepositoryTest {

    @Autowired
    CommodityRepository commodityRepository;

    @Test
    @Transactional
    public void saveTest() {
        CommodityType saveMeType = new CommodityType();
        saveMeType.setName("fake type");
        saveMeType.setDescription("fake description");


        Commodity saveMe = new Commodity();
        saveMe.setName("fake name");
        saveMe.setDescription("fake description");

        commodityRepository.save(saveMe);
    }
}
