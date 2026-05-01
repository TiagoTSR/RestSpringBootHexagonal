package com.decodex.br.adapters.out.persistence.mapper;

import com.decodex.br.adapters.out.persistence.entity.UsuarioEntity;
import com.decodex.br.domain.model.Role;
import com.decodex.br.domain.model.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {}

    public static Usuario toDomain(UsuarioEntity e) {
        return new Usuario(
                e.getId(),
                e.getUsername(),
                e.getPassword(),
                Role.valueOf(e.getRole())
        );
    }

    public static UsuarioEntity toEntity(Usuario u,String passwordHash) {
        UsuarioEntity e = new UsuarioEntity();
        e.setId(u.getId());
        e.setUsername(u.getUsername());
        e.setPassword(passwordHash);
        e.setRole(u.getRole().name());
        return e;
    }
}