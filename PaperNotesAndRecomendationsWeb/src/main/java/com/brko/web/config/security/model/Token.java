package com.brko.web.config.security.model;

/**
 * The token that is sent to the user and that is used for validation.
 */
public class Token {

    private String token;
    private long expires;

    public Token(String token, long expires) {
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }
}
