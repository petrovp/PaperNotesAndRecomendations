package com.brko.web.config.security;

import com.brko.service.persistance.datamodel.User;
import com.brko.service.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by ppetrov on 11/4/2016.
 */
@Component
public class UserProvider {

    @Autowired
    private UserRepository userRepository;

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        return userRepository.findByEmail(principal.getUsername());
    }
}
