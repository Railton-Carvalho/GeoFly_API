package com.rocket.rain.apigateaway.config;

import com.rocket.rain.apigateaway.serialization.converter.YamlJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static  final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        #Content Negotiation Via QueryParam
//        configurer.favorParameter(true)
//                  .parameterName("mediaType")
//                  .ignoreAcceptHeader(true)
//                  .useRegisteredExtensionsOnly(false)
//                  .defaultContentType(MediaType.APPLICATION_JSON)
//                  .mediaType("json",MediaType.APPLICATION_JSON)
//                  .mediaType("xml",MediaType.APPLICATION_XML);

        //Content Negotiation Via HeaderParam
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MEDIA_TYPE_APPLICATION_YML)
                .mediaType("json",MediaType.APPLICATION_JSON)
                .mediaType("xml",MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);

    }
}
