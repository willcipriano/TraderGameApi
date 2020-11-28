package rest.trader.traderapi.utility;

import org.springframework.stereotype.Service;
import rest.trader.traderapi.dto.dev.UniverseSeedDTO;
import rest.trader.traderapi.entity.Universe;
import rest.trader.traderapi.entity.commodity.Commodity;
import rest.trader.traderapi.entity.commodity.CommodityType;
import rest.trader.traderapi.exception.UniverseNotFoundException;
import rest.trader.traderapi.service.CommodityService;
import rest.trader.traderapi.service.CommodityTypeService;
import rest.trader.traderapi.service.UniverseService;

import java.util.*;

@Service
public class GenesisService {

    CommodityTypeService commodityTypeService;
    CommodityService commodityService;
    UniverseService universeService;
    Boolean bigRedButtonPressed = false;

    public GenesisService(CommodityTypeService commodityTypeService, CommodityService commodityService,
            UniverseService universeService) {
        this.commodityTypeService = commodityTypeService;
        this.commodityService = commodityService;
        this.universeService = universeService;
    }

    public Universe pressTheBigRedButton(UniverseSeedDTO universeSeedDTO) {
        if (!bigRedButtonPressed) {
            try {
                universeService.getUniverse();
            } catch (UniverseNotFoundException ex) {
                createUniverse(universeSeedDTO);
                bigRedButtonPressed = true;
                return universeService.createAndSaveUniverse(universeSeedDTO.getName(), universeSeedDTO);
            }
            bigRedButtonPressed = true;
        }
        return getCurrentUniverse();
    }

    private void createUniverse(UniverseSeedDTO universeSeedDTO) {
        createMinimumCommodities(universeSeedDTO);
        universeSeedDTO.setCompleted(true);
    }

    private void createMinimumCommodities(UniverseSeedDTO universeSeedDTO) {
        if (universeSeedDTO.getSkipMinimumCommodities() != null && universeSeedDTO.getSkipMinimumCommodities()) {
            universeSeedDTO.setMinimumCommoditiesCompletedSuccessfully(false);
        }

        Map<String, CommodityType> basicTypes = createBasicCommodityTypes();

        for (CommodityType commodityType : basicTypes.values()) {
            commodityTypeService.saveOrUpdate(commodityType);
        }

        List<Commodity> commodities = createBasicCommodity(basicTypes);

        for (Commodity commodity : commodities) {
            commodityService.saveOrUpdate(commodity);
        }

        universeSeedDTO.setMinimumCommoditiesCompletedSuccessfully(true);
    }

    private Map<String, CommodityType> createBasicCommodityTypes() {
        Map<String, CommodityType> types = new HashMap<>();

        CommodityType elemental = new CommodityType();
        elemental.setName("elemental");
        elemental.setDescription(
                "Pre-human commodities, the value of these items was known before man walked the earth.");

        CommodityType primordial = new CommodityType();
        primordial.setName("primordial");
        primordial.setDescription(
                "The most basic of human commodities, requiring the most basic level of effort and planning to collect and process.");

        CommodityType primitive = new CommodityType();
        primitive.setName("primitive");
        primitive.setDescription("Primitive commodities, the first true trade goods produced by human kind.");

        types.put("elemental", elemental);
        types.put("primordial", primordial);
        types.put("primitive", primitive);

        return types;
    }

    private Commodity createCommodity(String name, String description, CommodityType type) {
        Commodity commodity = new Commodity();
        commodity.setName(name);
        commodity.setDescription(description);
        commodity.setType(type);
        return commodity;
    }

    private List<Commodity> createBasicCommodity(Map<String, CommodityType> commodityTypes) {
        List<Commodity> commodities = new ArrayList<>();

        commodities.add(createCommodity("mud",
                "Soil, silt or clay mixed with water. Typically forms after rainfall or in proximity to water sources.",
                commodityTypes.get("elemental")));
        commodities.add(createCommodity("rainwater",
                "Water fallen as rain that has not collected soluble matter from the soil.",
                commodityTypes.get("elemental")));
        commodities.add(createCommodity("tinder",
                "Any dry substance that readily takes fire from a spark. Contains variously cedar bark, cattail fluff etc.",
                commodityTypes.get("primordial")));
        commodities
                .add(createCommodity("kindling", "Easily combustible small sticks or twigs used for starting a fire.",
                        commodityTypes.get("primordial")));

        return commodities;
    }

    public Universe getCurrentUniverse() throws UniverseNotFoundException {
        return universeService.getUniverse();
    }

}
