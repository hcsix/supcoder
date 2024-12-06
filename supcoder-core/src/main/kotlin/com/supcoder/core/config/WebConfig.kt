package com.supcoder.core.config

/**
 * WebConfig
 *
 * @author lee
 * @date 2024/12/6
 */
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfig : WebMvcConfigurer {

    @Value("\${allowed.origins}")
    private lateinit var allowedOrigins: String

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
            // 允许的前端地址
            .allowedOrigins(*allowedOrigins.split(",").toTypedArray())
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}