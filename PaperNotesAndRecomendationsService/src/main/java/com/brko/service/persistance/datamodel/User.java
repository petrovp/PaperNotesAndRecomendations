package com.brko.service.persistance.datamodel;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by ppetrov on 9/25/2016.
 */
@Document(collection = "user")
public class User extends NamedDocument {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
