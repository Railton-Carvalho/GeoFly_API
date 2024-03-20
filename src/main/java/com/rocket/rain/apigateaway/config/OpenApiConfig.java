package com.rocket.rain.apigateaway.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
                new Info()
                        .title("GeoFly APi")
                        .version("v1")
                        .description("Geo Api Information Management")
                        .termsOfService("https://api.rocketsquad.com.br/geoflyapi/terms")
                        .license(new License().name("Apache 2.0").url("https://api.rocketsquad.com.br/geoflyapi"))


        );
    }


}
