package com.sample.restapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sample.restapi.interceptor.ApiKeyInterceptor;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private ApiKeyInterceptor apiKeyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyInterceptor)
                .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**"); // Excluir la ruta de Swagger UI

    }
}