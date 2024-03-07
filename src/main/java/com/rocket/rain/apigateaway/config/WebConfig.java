package com.rocket.rain.apigateaway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        #Content Negotion Via QueryParam
//        configurer.favorParameter(true)
//                  .parameterName("mediaType")
//                  .ignoreAcceptHeader(true)
//                  .useRegisteredExtensionsOnly(false)
//                  .defaultContentType(MediaType.APPLICATION_JSON)
//                  .mediaType("json",MediaType.APPLICATION_JSON)
//                  .mediaType("xml",MediaType.APPLICATION_XML);

        //Content Negotion Via HeaderParam
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json",MediaType.APPLICATION_JSON)
                .mediaType("xml",MediaType.APPLICATION_XML);

    }
}
