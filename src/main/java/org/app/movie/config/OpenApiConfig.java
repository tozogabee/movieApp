package org.app.movie.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI movieApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movie API")
                        .description("API docs to movies")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Gabor Toth")
                                .email("tozogabee@gmail.com")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repo")
                        .url("https://github.com/tozogabee/movieApp"));
    }
}