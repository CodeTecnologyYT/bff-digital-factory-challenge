package pe.com.scotibank.bff.digital.factory.challenge.shared.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /**
     * Create Custom OpenAPI Configuration
     *
     * @return {@link OpenAPI}
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BFF Digital Factory Challenge")
                        .version("1.0.0")
                        .description("API management students with Spring WebFlux y R2DBC")
                        .contact(new Contact()
                                .name("Bryan Rosas")
                                .email("bryan.rosas.quispe@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }

}
