package com.medilabo.microserviceauthentication.service;

import com.medilabo.microserviceauthentication.exceptions.AuthUserAlreadyExistsException;
import com.medilabo.microserviceauthentication.models.AuthUser;
import com.medilabo.microserviceauthentication.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthUserService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUser addAuthUser(AuthUser authUser) throws AuthUserAlreadyExistsException {
        Optional <AuthUser> optionalAuthUser = authUserRepository.findAuthUserByUsernameEqualsIgnoreCase(authUser.getUsername());
        if (optionalAuthUser.isEmpty()) {
            authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
            return authUserRepository.save(authUser);
        }
        throw new AuthUserAlreadyExistsException();
    }

}
