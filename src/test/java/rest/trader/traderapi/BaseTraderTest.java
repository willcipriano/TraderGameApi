package rest.trader.traderapi;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import rest.trader.traderapi.dto.dev.UniverseSeedDTO;
import rest.trader.traderapi.entity.Universe;
import rest.trader.traderapi.entity.commodity.Commodity;
import rest.trader.traderapi.entity.commodity.CommodityType;
import rest.trader.traderapi.repository.*;

import java.time.LocalDateTime;

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

    @Autowired
    UniverseRepository universeRepository;

    private static String randomString(Integer minLength, Integer maxLength) {
        return new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(minLength, maxLength);
    }

    private static Boolean randomBool() {
        return Math.random() < 0.5;
    }

    public CommodityType createCommodityType(String name, String description) {
        CommodityType newCommodityType = CommodityType.builder().name(name).description(description).build();
        return commodityTypeRepository.save(newCommodityType);
    }

    public Commodity createCommodity(CommodityType commodityType, String name, String description) {
        Commodity newCommodity = Commodity.builder().type(commodityType).name(name).description(description).build();
        return commodityRepository.save(newCommodity);
    }

    public Universe createUniverse(String name, UniverseSeedDTO universeSeedDTO) {
        Universe newUniverse = Universe.builder().name(name).universeSeed(universeSeedDTO).created(LocalDateTime.now())
                .build();
        return universeRepository.save(newUniverse);
    }

    public UniverseSeedDTO createUniverseSeed(String name, Boolean skipMinimumCommodities, Boolean completed,
            Boolean minimumCommoditiesCompletedSuccessfully) {
        return UniverseSeedDTO.builder().name(name).skipMinimumCommodities(skipMinimumCommodities).completed(completed)
                .minimumCommoditiesCompletedSuccessfully(minimumCommoditiesCompletedSuccessfully).build();
    }

    public UniverseSeedDTO defaultUniverseSeed() {
        return createUniverseSeed("Default", false, false, false);
    }

    public CommodityType createFakeCommodityType() {
        return createCommodityType(randomString(8, 10), randomString(10, 35));
    }

    public Commodity createFakeCommodity() {
        return createCommodity(createFakeCommodityType(), randomString(5, 15), randomString(10, 35));
    }

    public Universe createFakeUniverse() {
        return createUniverse(randomString(5, 35), defaultUniverseSeed());
    }

}
