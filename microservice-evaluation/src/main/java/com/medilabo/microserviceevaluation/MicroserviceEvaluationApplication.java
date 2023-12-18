package com.medilabo.microserviceevaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceEvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceEvaluationApplication.class, args);
    }

}
