package com.decodex.br.domain.port.out;

import com.decodex.br.domain.model.Usuario;

public interface TokenPort {
    String generate(Usuario usuario);
    String extractUsername(String token);
    boolean isValid(String token, String username);
}