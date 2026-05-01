package com.decodex.br.domain.model;

import com.decodex.br.domain.port.out.PasswordCheckerPort;

public class Usuario {

    private Long id;
    private String username;
    private final String passwordHash;
    private Role role;

    public Usuario(Long id, String username, String passwordHash, Role role) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public boolean authenticate(String rawPassword, PasswordCheckerPort checker) {
        return checker.matches(rawPassword, this.passwordHash);
    }

    public Long getId()         { return id; }
    public String getUsername() { return username; }
    public Role getRole()       { return role; }
}