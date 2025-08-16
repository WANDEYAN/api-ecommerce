package br.com.ecommerce.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@OpenAPIDefinition(
    info  = @Info(
        title = "Api Ecommerce",
        version = "0.0",
        description = "This my-ecommerce backend (https://github.com/WANDEYAN/my-ecommerce) is just a simple project to learn Java."
    )
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        String webHost = System.getenv("WEB_HOST");
        // Constrói a URL do servidor. Usa "localhost:8080" como fallback se WEB_HOST não estiver definido.
        // A porta 9000- é adicionada porque o ambiente de preview parece usar essa estrutura de URL.
        String serverUrl = "https://9000-" + (webHost != null ? webHost : "localhost:8080");

        return new OpenAPI()
            .info(new io.swagger.v3.oas.models.info.Info().title("Ecommerce API").version("1.0.0").description("API para a aplicação de e-commerce"))
            .addServersItem(new Server().url(serverUrl).description("Servidor Principal da API"));
    }
    
}