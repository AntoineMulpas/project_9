package com.medilabo.microservice_api_gateways.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class AuthFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Value("${AUTH_IP_ADDRESS}")
    String AUTH_IP_ADDRESS;

    @Override
    public Mono <Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println(exchange.getRequest().getRemoteAddress().getPort());

        if (exchange.getRequest().getPath().toString().equals("/api/v1/authentication/auth")) {
            return chain.filter(exchange);
        }

        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            logger.info("Authorization Bearer is not present.");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String authorization = exchange.getRequest().getHeaders().get("Authorization").get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        String url = "http://" + AUTH_IP_ADDRESS +":8300/api/v1/authentication/signin";

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity <Void> requestEntity = new RequestEntity <>(headers, HttpMethod.GET, URI.create(url));

        try {
            restTemplate.exchange(requestEntity, String.class);
            System.out.println("OK");
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("ERROR");
            return exchange.getResponse().setComplete();
        }

        System.out.println("DONE");
        exchange.getResponse().getHeaders().clear();
        System.out.println(exchange.getResponse().getHeaders());
        return chain.filter(exchange);
    }
}
