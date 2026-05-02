package com.decodex.br.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "refresh_token")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Instant expiration;

    @Column(nullable = false)
    private boolean revoked = false;

    public Long getId()              { return id; }
    public String getToken()         { return token; }
    public String getUsername()      { return username; }
    public Instant getExpiration()   { return expiration; }
    public boolean isRevoked()       { return revoked; }
    public void setId(Long id)              { this.id = id; }
    public void setToken(String t)          { this.token = t; }
    public void setUsername(String u)       { this.username = u; }
    public void setExpiration(Instant e)    { this.expiration = e; }
    public void setRevoked(boolean r)       { this.revoked = r; }
}