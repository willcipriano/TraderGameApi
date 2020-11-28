package rest.trader.traderapi.entity.configuration;

import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rest.trader.traderapi.dto.CommodityDTO;
import rest.trader.traderapi.entity.commodity.Commodity;

@Configuration
public class MapperConfiguration {
    @Bean
    Mapper<Commodity, CommodityDTO> commodityDTOCommodityMapper() {
        return Mapping.from(Commodity.class).to(CommodityDTO.class).omitInDestination(CommodityDTO::getDtoClass)
                .omitInSource(Commodity::getType).mapper();
    }
}
