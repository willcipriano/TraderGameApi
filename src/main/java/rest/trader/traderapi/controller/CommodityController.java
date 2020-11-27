package rest.trader.traderapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.trader.traderapi.dto.CommodityDTO;
import rest.trader.traderapi.entity.commodity.Commodity;
import rest.trader.traderapi.service.CommodityService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/commodity")
public class CommodityController {

    CommodityService service;

    @Autowired
    public CommodityController(CommodityService commodityService) {
        this.service = commodityService;
    }

    @GetMapping(path = "/example")
    public CommodityDTO getCommodity() {
        return service.toCommodityDTO(service.getExampleCommodity());
    }

    @GetMapping(path = "/uuid/{uuid}")
    public Commodity getCommodityByUUID(@PathVariable UUID uuid) {
        return service.getCommodityByUUID(uuid);
    }

    @GetMapping(path = "/name/{name}")
    public Commodity getCommodityByName(@PathVariable String name) {
        return service.getCommodityByName(name);
    }

}
