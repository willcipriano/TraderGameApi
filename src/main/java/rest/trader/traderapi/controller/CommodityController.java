package rest.trader.traderapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.trader.traderapi.dto.CommodityDTO;
import rest.trader.traderapi.service.CommodityService;

import java.time.LocalDateTime;
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
        CommodityDTO result = service.toCommodityDTO(service.getExampleCommodity());
        result.setLastFetched(LocalDateTime.now());
        return result;
    }

    @GetMapping(path = "/uuid/{uuid}")
    public CommodityDTO getCommodityByUUID(@PathVariable UUID uuid) {
        return service.toCommodityDTO(service.getCommodityByUUID(uuid));
    }

    @GetMapping(path = "/name/{name}")
    public CommodityDTO getCommodityByName(@PathVariable String name) {
        return service.toCommodityDTO(service.getCommodityByName(name));
    }

}
