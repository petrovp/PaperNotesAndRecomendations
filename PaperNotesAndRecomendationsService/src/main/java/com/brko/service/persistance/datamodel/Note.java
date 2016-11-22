package com.brko.service.persistance.datamodel;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by ppetrov on 11/4/2016.
 */
@Document
public class Note extends NamedDocument implements Comparable<Note> {

    private String text;

    private Date createdOn;

    private User createdBy;

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(Note o) {
        return o.createdOn.compareTo(this.createdOn);
    }
}
