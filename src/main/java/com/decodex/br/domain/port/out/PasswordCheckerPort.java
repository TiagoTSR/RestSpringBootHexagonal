package com.decodex.br.domain.port.out;

public interface PasswordCheckerPort {
    boolean matches(String raw, String encoded);
}