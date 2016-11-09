package com.brko.web.api.controllers;

import com.brko.service.persistance.datamodel.User;
import com.brko.web.config.security.TokenProvider;
import com.brko.web.config.security.model.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Contoller for handling the login logic.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Token> authenticate(@RequestBody User user) {
        if (user != null) {
            String username = user.getEmail();
            String password = user.getPassword();

            if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
                UserDetails userFromDb =
                        userDetailsService.loadUserByUsername(username);

                if (userFromDb != null && passwordEncoder.matches(password, userFromDb.getPassword())) {
                    Token token =  tokenProvider.createToken(username);
                    return new ResponseEntity<Token>(token, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<Token>(tokenProvider.createInvalidToken(), HttpStatus.UNAUTHORIZED);
    }
}
