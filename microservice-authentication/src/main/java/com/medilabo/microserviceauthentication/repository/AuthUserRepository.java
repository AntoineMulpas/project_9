package com.medilabo.microserviceauthentication.repository;

import com.medilabo.microserviceauthentication.models.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findAuthUserByUsernameEqualsIgnoreCase(String username);

}
