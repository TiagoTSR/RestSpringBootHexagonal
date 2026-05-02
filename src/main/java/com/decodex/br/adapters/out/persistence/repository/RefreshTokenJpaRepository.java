package com.decodex.br.adapters.out.persistence.repository;

import com.decodex.br.adapters.out.persistence.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.time.Instant;
import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);

    @Modifying
    @Query("UPDATE RefreshTokenEntity r SET r.revoked = true WHERE r.username = :username AND r.revoked = false")
    void revokeAllByUsername(String username);

    @Modifying
    @Query("DELETE FROM RefreshTokenEntity r WHERE r.expiration < :now")
    void deleteByExpirationBefore(Instant now);
}