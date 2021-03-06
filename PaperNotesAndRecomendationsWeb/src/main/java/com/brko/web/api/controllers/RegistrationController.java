package com.brko.web.api.controllers;

import com.brko.service.persistance.datamodel.User;
import com.brko.service.services.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ppetrov on 10/8/2016.
 */
@CrossOrigin
@RestController
public class RegistrationController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User tryToRegister(@RequestBody User user) {
        return userService.tryToRegister(user);
    }
}
