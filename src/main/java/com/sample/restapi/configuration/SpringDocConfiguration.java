package com.sample.restapi.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.profiles.active}")
    private String environment;

    @Value("${application-version}")
    private String version;

    @Value("${application-description}")
    private String description;    

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
                .info(
                    new Info()
                        .version(version)
                        .title(description)
                        .description("<h3>Spring shop sample application (environment=<b>"+ environment + "</b>)</h3><h4>Info: This API requires an X-API-Key header</h4>")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                )
                .externalDocs(new ExternalDocumentation()
                .description("Swagger Documentation")
                .url("https://swagger.io/docs/"));
    }    
}