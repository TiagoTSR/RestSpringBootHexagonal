package com.decodex.br.domain.port.out;

import java.util.Optional;
import com.decodex.br.domain.model.RefreshToken;

public interface RefreshTokenRepositoryPort {
    RefreshToken save(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
    void revokeByUsername(String username);
    void deleteExpired();
}