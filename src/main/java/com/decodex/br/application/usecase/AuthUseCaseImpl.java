package com.decodex.br.application.usecase;

import com.decodex.br.application.dto.auth.*;
import com.decodex.br.domain.exception.CredenciaisInvalidasException;
import com.decodex.br.domain.exception.RefreshTokenInvalidoException;
import com.decodex.br.domain.model.RefreshToken;
import com.decodex.br.domain.model.Usuario;
import com.decodex.br.domain.port.in.AuthUseCase;
import com.decodex.br.domain.port.out.*;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class AuthUseCaseImpl implements AuthUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final TokenPort tokenPort;
    private final PasswordCheckerPort passwordChecker;
    private final RefreshTokenRepositoryPort refreshTokenRepository;
    private final long refreshTokenDias;

    public AuthUseCaseImpl(UsuarioRepositoryPort usuarioRepository,
                           TokenPort tokenPort,
                           PasswordCheckerPort passwordChecker,
                           RefreshTokenRepositoryPort refreshTokenRepository,
                           long refreshTokenDias) {
        this.usuarioRepository = usuarioRepository;
        this.tokenPort = tokenPort;
        this.passwordChecker = passwordChecker;
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenDias = refreshTokenDias;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(CredenciaisInvalidasException::new);

        if (!usuario.authenticate(request.password(), passwordChecker)) {
            throw new CredenciaisInvalidasException();
        }

        refreshTokenRepository.revokeByUsername(usuario.getUsername());

        String accessToken = tokenPort.generate(usuario);
        return new LoginResponse(accessToken, toResponse(usuario));
    }

    @Override
    public String gerarRefreshToken(String username) {
        RefreshToken rt = new RefreshToken(
                null,
                UUID.randomUUID().toString(),
                username,
                Instant.now().plusSeconds(86400L * refreshTokenDias),
                false
        );
        return refreshTokenRepository.save(rt).getToken();
    }

    @Override
    public LoginResponse refresh(String refreshTokenValue) {
        RefreshToken rt = refreshTokenRepository.findByToken(refreshTokenValue)
                .orElseThrow(RefreshTokenInvalidoException::new);

        if (!rt.isValid()) {
            throw new RefreshTokenInvalidoException();
        }

        Usuario usuario = usuarioRepository.findByUsername(rt.getUsername())
                .orElseThrow(RefreshTokenInvalidoException::new);

        // Rotação: revoga o atual e emite novo
        refreshTokenRepository.revokeByUsername(usuario.getUsername());

        String newAccessToken = tokenPort.generate(usuario);
        return new LoginResponse(newAccessToken, toResponse(usuario));
    }

    @Override
    public void logout(String refreshTokenValue) {
        refreshTokenRepository.findByToken(refreshTokenValue)
                .ifPresent(rt -> refreshTokenRepository.revokeByUsername(rt.getUsername()));
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(u.getId(), u.getUsername(), u.getRole().name());
    }
}