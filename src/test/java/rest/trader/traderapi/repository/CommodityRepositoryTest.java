package rest.trader.traderapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rest.trader.traderapi.entity.commodity.Commodity;
import rest.trader.traderapi.entity.commodity.CommodityType;
import rest.trader.traderapi.BaseTraderTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommodityRepositoryTest extends BaseTraderTest {

    @Autowired
    CommodityRepository commodityRepository;

    private void createCommodity() {
        CommodityType commodityType = createFakeCommodityType();

        Commodity commodity = new Commodity();
        commodity.setName("Fake Commodity");
        commodity.setDescription("This is a fake entry");
        commodity.setType(commodityType);

        commodityRepository.save(commodity);
    }

    @Test
    @Transactional
    void findByName() {
        createCommodity();
        Commodity result = commodityRepository.findByName("Fake Commodity");
        assertThat(result.getDescription()).isEqualTo("This is a fake entry");
    }

    @Test
    @Transactional
    void findByDescription() {
        createCommodity();
        Commodity result = commodityRepository.findByDescription("This is a fake entry");
        assertThat(result.getName()).isEqualTo("Fake Commodity");
    }
}
