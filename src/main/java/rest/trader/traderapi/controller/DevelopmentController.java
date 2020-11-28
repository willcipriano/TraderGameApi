package rest.trader.traderapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.trader.traderapi.dto.ApiErrorDTO;
import rest.trader.traderapi.dto.dev.UniverseSeedDTO;
import rest.trader.traderapi.entity.Universe;
import rest.trader.traderapi.exception.UniverseNotFoundException;
import rest.trader.traderapi.utility.GenesisDevice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/setecAstronomy")
public class DevelopmentController {

    private static final UniverseSeedDTO emptyUniverseSeed = UniverseSeedDTO.builder().completed(false).failed(false)
            .skipMinimumCommodities(false).dtoClass("universeSeedDto").name("Empty Seed").build();

    GenesisDevice genesisDevice;

    private final static UniverseSeedDTO basicSeed = new UniverseSeedDTO(emptyUniverseSeed);

    private final static int universeCacheDays = 30;

    @Autowired
    public DevelopmentController(GenesisDevice genesisDevice) {
        this.genesisDevice = genesisDevice;
        basicSeed.setName("Default Seed");
    }

    @PostMapping(path = "/genesisDevice")
    @ApiOperation(value = "Let there be light.", response = UniverseSeedDTO.class)
    public Universe activateGenesisDevice() {
        return genesisDevice.pressTheBigRedButton(basicSeed);
    }

    @PostMapping(path = "/genesisDevice/seed")
    @ApiOperation(value = "Let there be light, but with options.", response = UniverseSeedDTO.class)
    public Universe genesisDeviceControlledActivation(
            @ApiParam(value = "universeSeedDTO", required = true) @RequestBody UniverseSeedDTO universeSeedDTO) {
        return genesisDevice.pressTheBigRedButton(universeSeedDTO);
    }

    @GetMapping(path = "/universe")
    public ResponseEntity<Object> getUniverse() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .cacheControl(CacheControl.maxAge(universeCacheDays, TimeUnit.DAYS))
                    .body(genesisDevice.getCurrentUniverse());
        } catch (UniverseNotFoundException ex) {
            ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().dtoClass("error").dtoClassName("universe")
                    .error(ex.getMessage()).updated(LocalDateTime.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).cacheControl(CacheControl.noCache()).body(apiErrorDTO);
        }
    }
}
