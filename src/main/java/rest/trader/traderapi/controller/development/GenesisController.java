package rest.trader.traderapi.controller.development;

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
import rest.trader.traderapi.utility.GenesisService;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/v-1/genesis")
public class GenesisController {

    private static final UniverseSeedDTO emptyUniverseSeed = UniverseSeedDTO.builder().dtoClass("universeSeed").name("Empty Seed").build();
    GenesisService genesisService;
    private final static UniverseSeedDTO basicSeed = new UniverseSeedDTO(emptyUniverseSeed);
    private final static int universeCacheDays = 30;

    @Autowired
    public GenesisController(GenesisService genesisService) {
        this.genesisService = genesisService;
        basicSeed.setName("Default Seed");
    }

    @PostMapping()
    @ApiOperation(value = "Let there be light.", response = Universe.class)
    public Universe activateGenesisDevice() {
        return genesisService.pressTheBigRedButton(basicSeed);
    }

    @PostMapping(path = "/seed")
    @ApiOperation(value = "Let there be light, but with options.", response = Universe.class)
    public Universe genesisDeviceControlledActivation(
            @ApiParam(value = "universeSeedDTO", required = true) @RequestBody UniverseSeedDTO universeSeedDTO) {
        return genesisService.pressTheBigRedButton(universeSeedDTO);
    }

    @GetMapping(path = "/universe")
    @ApiOperation(value = "Fetch the current universe.", response = Universe.class)
    public ResponseEntity<Object> getUniverse() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .cacheControl(CacheControl.maxAge(universeCacheDays, TimeUnit.DAYS))
                    .body(genesisService.getCurrentUniverse());
        } catch (UniverseNotFoundException ex) {
            ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().dtoClass("error").dtoClassName("universe")
                    .error(ex.getMessage()).updated(LocalDateTime.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).cacheControl(CacheControl.noCache()).body(apiErrorDTO);
        }
    }
}
