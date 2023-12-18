package com.medilabo.microservice_api_gateways.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("*"));
        corsConfig.setMaxAge(8000L);
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(p ->
                        p.path("/api/v1/authentication/**").uri("lb://microservice-authentication"))
                .route(p ->
                        p.path("/get")
                                .filters(f -> f.addRequestHeader("Token", "MyToken").addRequestParameter("Parameter", "MyParameter"))
                                .uri("http://httpbin.org:80"))
                .route(p ->
                        p.path("/api/v1/patient/**").uri("lb://microservice-patients"))
                .route(p ->
                        p.path("/api/v1/note/**").uri("lb://microservice-notes"))
                .route(p -> p.path("/api/v1/evaluation/**").uri("lb://microservice-evaluation"))
                .build();
    }

}
