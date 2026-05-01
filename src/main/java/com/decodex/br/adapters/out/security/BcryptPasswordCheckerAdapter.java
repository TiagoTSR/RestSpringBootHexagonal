package com.decodex.br.adapters.out.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.decodex.br.domain.port.out.PasswordCheckerPort;

public class BcryptPasswordCheckerAdapter implements PasswordCheckerPort {


    private final PasswordEncoder encoder;

    public BcryptPasswordCheckerAdapter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public boolean matches(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }
}