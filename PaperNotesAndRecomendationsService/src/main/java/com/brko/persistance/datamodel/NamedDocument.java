package com.brko.persistance.datamodel;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedDocument extends BaseDocument{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
