package com.brko.service.services.user;

import com.brko.service.persistance.datamodel.User;

/**
 * Created by ppetrov on 10/31/2016.
 */
public interface UserService {

    public User tryToRegister(User user);
}
