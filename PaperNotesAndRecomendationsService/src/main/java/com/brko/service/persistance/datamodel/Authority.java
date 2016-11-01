package com.brko.service.persistance.datamodel;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ppetrov on 11/1/2016.
 */
@Document
public class Authority extends NamedDocument {

    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
