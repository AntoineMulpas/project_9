package com.medilabo.microserviceauthentication.controller;

import com.medilabo.microserviceauthentication.models.AuthUser;
import com.medilabo.microserviceauthentication.models.UserInfoResponse;
import com.medilabo.microserviceauthentication.security.jwt.JwtUtils;
import com.medilabo.microserviceauthentication.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/authentication")
public class AuthController {

    private final AuthUserService authUserService;

    @Autowired
    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/signin")
    public ResponseEntity <?> confirmAuthentication() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(username);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(
                        username));
    }


    @GetMapping("/getToken")
    public String getToken() {
        return jwtUtils.generateTokenFromUsername("antoine");
    }





}
