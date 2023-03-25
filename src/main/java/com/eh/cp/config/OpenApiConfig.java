package com.eh.cp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eh.cp.helper.constants.CommonConstants;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
/**
* @author MD. EMRAN HOSSAIN
* @since 24 MAR, 2023
* @version 1.1
*/
@Configuration
@SecurityScheme(name = CommonConstants.SECURITY, type = SecuritySchemeType.HTTP, bearerFormat = CommonConstants.BEARER_FORMAT, scheme = CommonConstants.BEARER_SCHEME)
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPIConfiguration(){
        final String securitySchemeName = CommonConstants.SECURITY;

        return new OpenAPI().components(new Components())
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .info(new Info().title(CommonConstants.SWAGGER_TITLE).description(CommonConstants.SWAGGER_DESCRIPTION)
                .termsOfService(CommonConstants.SWAGGER_TEAMS_OF_SERVICE)
                .license(new License().name(CommonConstants.SWAGGER_LICENSE).url(CommonConstants.SWAGGER_COMPANY_URL)));
    }
}
