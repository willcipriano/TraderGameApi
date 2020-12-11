package rest.trader.traderapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.trader.traderapi.dto.ApiErrorDTO;
import rest.trader.traderapi.dto.CommodityDTO;
import rest.trader.traderapi.entity.commodity.Commodity;
import rest.trader.traderapi.exception.CommodityNotFoundException;
import rest.trader.traderapi.exception.ObjectNotFoundException;
import rest.trader.traderapi.service.CommodityService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/v1/commodities")
@Api(value = "commodities")
public class CommodityController {
    CommodityService service;
    CommodityDTO exampleCommodityDto;
    private static final int CACHEHOURS = 3;
    private static final String CLASSNAME = "commodity";

    @Autowired
    public CommodityController(CommodityService commodityService) {
        this.service = commodityService;
        this.exampleCommodityDto = this.service.toCommodityDTO(this.service.getExampleCommodity());
    }

    @ApiOperation(value = "Get commodity by UUID.", response = CommodityDTO.class)
    @GetMapping(value = "/uuid/{uuid}", produces = "application/json")
    public ResponseEntity<Object> getCommodityByUUID(
            @ApiParam(value = "uuid", required = true, example = "97430852-3d04-47c7-bb29-bea7a8f51ba8") @PathVariable UUID uuid) {
        try {
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(CACHEHOURS, TimeUnit.HOURS))
                    .body(service.toCommodityDTO(service.getCommodityByUUID(uuid)));
        } catch (CommodityNotFoundException ex) {
            ApiErrorDTO errorDTO = ApiErrorDTO.builder().dtoClassName(CLASSNAME).error(ex.getMessage())
                    .updated(LocalDateTime.now()).build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .cacheControl(CacheControl.maxAge(CACHEHOURS, TimeUnit.HOURS)).body(errorDTO);
        }
    }

    @ApiOperation(value = "Get commodity by name.", response = CommodityDTO.class)
    @GetMapping(value = "/name/{name}", produces = "application/json")
    public ResponseEntity<Object> getCommodityByName(
            @ApiParam(value = "name", required = true, example = "Quartz") @PathVariable String name) {
        try {
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(CACHEHOURS, TimeUnit.HOURS))
                    .body(service.toCommodityDTO(service.getCommodityByName(name)));
        } catch (CommodityNotFoundException ex) {
            ApiErrorDTO errorDTO = ApiErrorDTO.builder().dtoClassName(CLASSNAME).error(ex.getMessage())
                    .updated(LocalDateTime.now()).build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .cacheControl(CacheControl.maxAge(CACHEHOURS, TimeUnit.HOURS)).body(errorDTO);
        }
    }

    @ApiOperation(value = "Get a example commodity.", response = CommodityDTO.class)
    @GetMapping(value = "/example", produces = "application/json")
    public ResponseEntity<CommodityDTO> getExampleCommodity() {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(31, TimeUnit.DAYS)).body(exampleCommodityDto);
    }

    @ApiOperation(value = "Get a list of commodities by type UUID.", response = CommodityDTO.class, responseContainer = "List")
    @GetMapping(value = "/type/uuid/{uuid}", produces = "application/json")
    public ResponseEntity<Object> getCommoditiesByTypeUuid(
            @ApiParam(name = "uuid", required = true, example = "38f3c0a2-344b-4271-9389-1241e2e03c5c") @PathVariable UUID uuid,
            Pageable pageable) {
        try {
            List<CommodityDTO> result = service.toCommodityDtos(service.getCommoditiesByTypeUuid(uuid, pageable));

            if (result.isEmpty()) {
                return ResponseEntity.ok().cacheControl(CacheControl.maxAge(CACHEHOURS, TimeUnit.HOURS)).body(result);
            }
        } catch (ObjectNotFoundException ex) {
            ApiErrorDTO errorDTO = ApiErrorDTO.builder().dtoClassName(CLASSNAME)
                    .error("Error fetching commodity list by type: [" + ex.getMessage() + "]")
                    .updated(LocalDateTime.now()).build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .cacheControl(CacheControl.maxAge(CACHEHOURS, TimeUnit.HOURS)).body(errorDTO);
        }

        ApiErrorDTO errorDTO = ApiErrorDTO.builder().dtoClassName(CLASSNAME)
                .error("CommodityType UUID: '" + uuid + "' does not have any associated commodities.")
                .updated(LocalDateTime.now()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).cacheControl(CacheControl.maxAge(CACHEHOURS, TimeUnit.HOURS))
                .body(errorDTO);
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get a list of commodities.", response = CommodityDTO.class, responseContainer = "List")
    public ResponseEntity<List<CommodityDTO>> getCommodities(Pageable pageable) {
        Page<Commodity> results = service.findAll(pageable);
        List<CommodityDTO> response = new ArrayList<>();

        for (Commodity result : results) {
            CommodityDTO commodityDTO = service.toCommodityDTO(result);
            response.add(commodityDTO);
        }
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(CACHEHOURS, TimeUnit.HOURS)).body(response);
    }

}
