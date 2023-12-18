package com.medilabo.microservice_cloud_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MicroserviceCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCloudConfigApplication.class, args);
    }

}
