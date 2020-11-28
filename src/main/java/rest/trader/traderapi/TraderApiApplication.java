package rest.trader.traderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.servlet.Filter;

@SpringBootApplication
public class TraderApiApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/api");
        SpringApplication.run(TraderApiApplication.class, args);
    }

    @Bean
    public Filter filter() {
        return new ShallowEtagHeaderFilter();
    }
}
