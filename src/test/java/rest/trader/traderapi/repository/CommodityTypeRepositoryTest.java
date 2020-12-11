package rest.trader.traderapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rest.trader.traderapi.BaseTraderTest;
import rest.trader.traderapi.entity.commodity.CommodityType;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommodityTypeRepositoryTest extends BaseTraderTest {

    @Autowired
    CommodityTypeRepository repository;

    @Test
    @Transactional
    void createCommodityType() {
        CommodityType commodityType = new CommodityType();
        commodityType.setName("Fake Commodity Type");
        commodityType.setDescription("Fake Commodity Type Details");
        repository.save(commodityType);
    }

    @Test
    @Transactional
    void findByName() {
        createCommodityType();
        CommodityType result = repository.findByName("Fake Commodity Type");
        assertThat(result.getDescription()).isEqualTo("Fake Commodity Type Details");
    }

}
