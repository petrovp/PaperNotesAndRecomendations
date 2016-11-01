package com.brko.web.config.security;

import com.brko.web.config.security.model.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The Token provider.
 */
public class TokenProvider {

    private final String secretKey;
    private final int tokenValidity;

    public TokenProvider(String secretKey, int tokenValidity) {
        this.secretKey = secretKey;
        this.tokenValidity = tokenValidity;
    }

    public Token createToken(String username) {
        long expires = System.currentTimeMillis() + 1000L * tokenValidity;
        String token = username + ":" + expires + ":" + computeSignature(username, expires);
        return new Token(token, expires);
    }

    public Token createInvalidToken() {
        return new Token("", -1L);
    }

    public String computeSignature(String username, long expires) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(username).append(":");
        signatureBuilder.append(expires).append(":");
        signatureBuilder.append(secretKey);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No SHA-256 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
    }

    public String getUserNameFromToken(String authToken) {
        if (null == authToken) {
            return null;
        }
        String[] parts = authToken.split(":");
        return parts[0];
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        String[] parts = authToken.split(":");
        long expires = Long.parseLong(parts[1]);
        String signature = parts[2];
        String signatureToMatch = computeSignature(userDetails.getUsername(), expires);
        return expires >= System.currentTimeMillis() && signature.equals(signatureToMatch);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public int getTokenValidity() {
        return tokenValidity;
    }
}
