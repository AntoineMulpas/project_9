package com.medilabo.microserviceauthentication.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "AuthUser")
@Table(name = "auth_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String password;
    private String role;

    public AuthUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
