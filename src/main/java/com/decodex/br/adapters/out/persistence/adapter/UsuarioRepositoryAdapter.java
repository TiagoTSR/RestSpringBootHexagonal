package com.decodex.br.adapters.out.persistence.adapter;

import java.util.Optional;

import com.decodex.br.adapters.out.persistence.entity.UsuarioEntity;
import com.decodex.br.adapters.out.persistence.mapper.UsuarioMapper;
import com.decodex.br.adapters.out.persistence.repository.UsuarioJpaRepository;
import com.decodex.br.domain.model.Usuario;
import com.decodex.br.domain.port.out.UsuarioRepositoryPort;

public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository jpa;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return jpa.findByUsername(username)
                .map(UsuarioMapper::toDomain);
    }

    public Optional<String> findPasswordHashByUsername(String username) {
        return jpa.findByUsername(username)
                .map(UsuarioEntity::getPassword);
    }
}