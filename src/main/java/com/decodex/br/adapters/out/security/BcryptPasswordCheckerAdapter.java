package com.decodex.br.adapters.out.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.decodex.br.adapters.in.web.exception.GlobalExceptionHandler;
import com.decodex.br.domain.port.out.PasswordCheckerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BcryptPasswordCheckerAdapter implements PasswordCheckerPort {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    private final PasswordEncoder encoder;

    public BcryptPasswordCheckerAdapter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public boolean matches(String raw, String encoded) {
        log.info("raw: '{}'", raw);
        log.info("encoded: '{}'", encoded);
        log.info("result: {}", encoder.matches(raw, encoded));
        return encoder.matches(raw, encoded);
    }
}