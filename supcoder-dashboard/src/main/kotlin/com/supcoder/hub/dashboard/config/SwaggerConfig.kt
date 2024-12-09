package com.supcoder.hub.dashboard.config

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Swagger2Configuration
 *
 * @author lee
 * @date 2024/12/9
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
open class SwaggerConfig {

    @Bean
    open fun adminDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .groupName("dashboard")
            .apiInfo(dashboardApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.supcoder.hub.dashboard.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private fun dashboardApiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("SUPCODER-DASHBOARD API")
            .description("SUPCODER管理后台API")
            .termsOfServiceUrl("https://github.com/hcsix/supcoder")
            .contact("https://github.com/hcsix/supcoder")
            .version("1.0")
            .build();
    }
}