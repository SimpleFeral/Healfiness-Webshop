package com.healfiness.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Healfiness Webshop APP")
                        .version("1.0.0")
                        .description("Enterprise REST API for Healfiness Webshop")
                )
                .servers(List.of(
                                new Server().url("http://localhost:8080/api/v1").description("Local development server"),
                                new Server().url("https://api.healfiness.com/api/v1").description("Production")
                        )
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth")
                        .addList("basicAuth")
                )
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", bearerScheme())
                        .addSecuritySchemes("basicAuth", basicScheme())
                );
    }

    private SecurityScheme bearerScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

    private SecurityScheme basicScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic");
    }
}
