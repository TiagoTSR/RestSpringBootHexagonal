package com.decodex.br.adapters.out.persistence.adapter;

import com.decodex.br.adapters.out.persistence.mapper.RefreshTokenMapper;
import com.decodex.br.adapters.out.persistence.repository.RefreshTokenJpaRepository;
import com.decodex.br.domain.model.RefreshToken;
import com.decodex.br.domain.port.out.RefreshTokenRepositoryPort;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.Optional;

public class RefreshTokenRepositoryAdapter implements RefreshTokenRepositoryPort {

    private final RefreshTokenJpaRepository jpa;

    public RefreshTokenRepositoryAdapter(RefreshTokenJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public RefreshToken save(RefreshToken token) {
        return RefreshTokenMapper.toDomain(jpa.save(RefreshTokenMapper.toEntity(token)));
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return jpa.findByToken(token).map(RefreshTokenMapper::toDomain);
    }

    @Override
    @Transactional
    public void revokeByUsername(String username) {
        jpa.revokeAllByUsername(username);
    }

    @Override
    @Transactional
    public void deleteExpired() {
        jpa.deleteByExpirationBefore(Instant.now());
    }
}