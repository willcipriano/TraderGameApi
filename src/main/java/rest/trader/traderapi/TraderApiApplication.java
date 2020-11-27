package rest.trader.traderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.mappers.ModelMapper;

@SpringBootApplication
public class TraderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraderApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
