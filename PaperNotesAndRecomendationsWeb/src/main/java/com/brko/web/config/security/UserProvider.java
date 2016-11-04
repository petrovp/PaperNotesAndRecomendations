package com.brko.web.config.security;

import com.brko.service.persistance.datamodel.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by ppetrov on 11/4/2016.
 */
@Component
public class UserProvider {

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        return principal;
    }
}
