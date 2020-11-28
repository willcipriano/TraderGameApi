package rest.trader.traderapi.entity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Will Cipriano", "https://github.com/willcipriano",
            "will@willcipriano.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Trader.REST", "Trader.REST Swagger Documentation",
            "0.0", "TDB", DEFAULT_CONTACT, "TDB", "TDB", Arrays.asList());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(Set.of("application/json"))
                .consumes(Set.of("application/json")).select()
                .apis(RequestHandlerSelectors.basePackage("rest.trader.traderapi")).paths(PathSelectors.any()).build();
    }
}