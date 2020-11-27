package rest.trader.traderapi;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import rest.trader.traderapi.entity.commodity.Commodity;
import rest.trader.traderapi.entity.commodity.CommodityType;
import rest.trader.traderapi.repository.*;

public class BaseTraderTest {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    CommodityTypeRepository commodityTypeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    LedgerEntryRepository ledgerEntryRepository;

    @Autowired
    LedgerRepository ledgerRepository;

    private static String randomString(Integer minLength, Integer maxLength) {
        return new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(minLength, maxLength);
    }

    public CommodityType createCommodityType(String name, String description) {
        CommodityType newCommodityType = new CommodityType();
        newCommodityType.setName(name);
        newCommodityType.setDescription(description);
        return commodityTypeRepository.save(newCommodityType);
    }

    public Commodity createCommodity(CommodityType commodityType, String name, String description) {
        Commodity newCommodity = new Commodity();
        newCommodity.setType(commodityType);
        newCommodity.setName(name);
        newCommodity.setDescription(description);
        return commodityRepository.save(newCommodity);
    }

    public CommodityType createFakeCommodityType() {
        return createCommodityType(randomString(8, 10), randomString(10, 35));
    }

    public Commodity createFakeCommodity() {
        return createCommodity(createFakeCommodityType(), randomString(5, 15), randomString(10, 35));
    }

}
