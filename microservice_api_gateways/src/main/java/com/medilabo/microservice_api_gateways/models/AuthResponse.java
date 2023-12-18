package com.medilabo.microservice_api_gateways.models;

public class AuthResponse {

    private String username;

    public AuthResponse(String username) {
        this.username = username;
    }

    public AuthResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "username='" + username + '\'' +
                '}';
    }
}
