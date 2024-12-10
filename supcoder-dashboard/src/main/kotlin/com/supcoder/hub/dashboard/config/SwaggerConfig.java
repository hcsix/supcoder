package com.supcoder.hub.dashboard.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig
 *
 * @author lee
 * @date 2024/12/10
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Supcoder Dashboard API")
                        .version("1.0.0")
                        .description("This is a sample Spring Boot RESTful service using springdoc-openapi and Swagger UI."));
    }
}
