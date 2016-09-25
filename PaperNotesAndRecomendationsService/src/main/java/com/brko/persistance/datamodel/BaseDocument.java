package com.brko.persistance.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseDocument {

    @Id
    protected String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BaseDocument)) {
            return false;
        }
        BaseDocument be = (BaseDocument) obj;

        if (this.getId() == null || be.getId() == null) {
            return super.equals(obj);
        } else {
            return this.getId().equals(be.getId());
        }
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return this.id != null ? (this.getClass() + "-" + this.id).hashCode()
                : super.hashCode();
    }
}
