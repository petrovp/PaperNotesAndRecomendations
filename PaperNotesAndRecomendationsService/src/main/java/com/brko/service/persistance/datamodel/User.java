package com.brko.service.persistance.datamodel;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by ppetrov on 9/25/2016.
 */
@Document(collection = "user")
public class User extends NamedDocument {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Authority authority;

    private List<PaperSummary> suggestedPapers;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public List<PaperSummary> getSuggestedPapers() {
        return suggestedPapers;
    }

    public void setSuggestedPapers(List<PaperSummary> suggestedPapers) {
        this.suggestedPapers = suggestedPapers;
    }
}
