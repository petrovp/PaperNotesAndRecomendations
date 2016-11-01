package com.brko.service.services.user.impl;

import com.brko.service.persistance.datamodel.User;
import com.brko.service.persistance.repository.UserRepository;
import com.brko.service.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ppetrov on 10/31/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User tryToRegister(User user) {
        User byEmail = userRepository.findByEmail(user.getEmail());

        if (byEmail == null) {
            User savedUser = userRepository.save(user);
            return savedUser;
        }

        return byEmail;
    }
}
