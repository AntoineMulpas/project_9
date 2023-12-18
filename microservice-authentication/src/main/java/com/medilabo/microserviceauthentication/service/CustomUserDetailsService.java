package com.medilabo.microserviceauthentication.service;

import com.medilabo.microserviceauthentication.models.AuthUser;
import com.medilabo.microserviceauthentication.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    @Autowired
    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <AuthUser> optionalAuthUser = authUserRepository.findAuthUserByUsernameEqualsIgnoreCase(username);
        if (optionalAuthUser.isPresent()) {
            return new User(
                    optionalAuthUser.get().getUsername(),
                    optionalAuthUser.get().getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(optionalAuthUser.get().getRole()))
            );
        }
        throw new UsernameNotFoundException("User not found.");
    }
}
