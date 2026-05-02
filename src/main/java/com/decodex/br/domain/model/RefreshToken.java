package com.decodex.br.domain.model;

import java.time.Instant;

public class RefreshToken {

    private Long id;
    private String token;
    private String username;
    private Instant expiration;
    private boolean revoked;

    public RefreshToken(Long id, String token, String username, Instant expiration, boolean revoked) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.expiration = expiration;
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiration);
    }

    public boolean isValid() {
        return !revoked && !isExpired();
    }

    public Long getId()           { return id; }
    public String getToken()      { return token; }
    public String getUsername()   { return username; }
    public Instant getExpiration(){ return expiration; }
    public boolean isRevoked()    { return revoked; }
}