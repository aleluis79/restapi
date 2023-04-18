package com.sample.restapi.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("api_key", new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .description("Api Key access")
                        .in(SecurityScheme.In.HEADER)
                        .name("X-API-Key")
                )          )
                .security(Arrays.asList(new SecurityRequirement().addList("api_key")))
                .info(new Info().title("SpringShop API")
                .description("Spring shop sample application")
                .version("v1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                .description("SpringShop Wiki Documentation")
                .url("https://springshop.wiki.github.org/docs"));
    }    
}