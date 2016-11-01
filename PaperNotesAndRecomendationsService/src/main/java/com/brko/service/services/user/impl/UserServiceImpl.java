package com.brko.service.services.user.impl;

import com.brko.service.persistance.datamodel.AuthoritiesConstants;
import com.brko.service.persistance.datamodel.Authority;
import com.brko.service.persistance.datamodel.User;
import com.brko.service.persistance.repository.UserRepository;
import com.brko.service.services.user.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ppetrov on 10/31/2016.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User tryToRegister(User user) {
        User byEmail = userRepository.findByEmail(user.getEmail());

        if (byEmail == null) {
            Authority authority = new Authority();
            authority.setName(AuthoritiesConstants.USER);
            user.setAuthority(authority);

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            User savedUser = userRepository.save(user);
            return savedUser;
        }

        return byEmail;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFromDatabase = userRepository.findByEmail(email);

        if (userFromDatabase != null) {
            Authority authority = userFromDatabase.getAuthority();
            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));

            return new org.springframework.security.core.userdetails.User(email, userFromDatabase.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User with username "+ email + " was not found in the database.");
        }
    }
}
