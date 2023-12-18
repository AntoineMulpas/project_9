package com.medilabo.microserviceauthentication;

import com.medilabo.microserviceauthentication.models.AuthUser;
import com.medilabo.microserviceauthentication.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceAuthenticationApplication implements CommandLineRunner {

    private final AuthUserService authUserService;

    @Autowired
    public MicroserviceAuthenticationApplication(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceAuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            authUserService.addAuthUser(new AuthUser("antoine", "password123", "ADMIN"));
            authUserService.addAuthUser(new AuthUser("openclassrooms", "password321", "ADMIN"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
