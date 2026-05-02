package com.decodex.br.adapters.out.persistence.mapper;

import com.decodex.br.adapters.out.persistence.entity.RefreshTokenEntity;
import com.decodex.br.domain.model.RefreshToken;

public class RefreshTokenMapper {

    private RefreshTokenMapper() {}

    public static RefreshToken toDomain(RefreshTokenEntity e) {
        return new RefreshToken(e.getId(), e.getToken(), e.getUsername(), e.getExpiration(), e.isRevoked());
    }

    public static RefreshTokenEntity toEntity(RefreshToken r) {
        RefreshTokenEntity e = new RefreshTokenEntity();
        e.setId(r.getId());
        e.setToken(r.getToken());
        e.setUsername(r.getUsername());
        e.setExpiration(r.getExpiration());
        e.setRevoked(r.isRevoked());
        return e;
    }
}