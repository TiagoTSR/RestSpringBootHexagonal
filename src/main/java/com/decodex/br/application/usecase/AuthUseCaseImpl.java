package com.decodex.br.application.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.decodex.br.application.dto.auth.LoginRequest;
import com.decodex.br.application.dto.auth.LoginResponse;
import com.decodex.br.application.dto.auth.UsuarioResponse;
import com.decodex.br.domain.exception.CredenciaisInvalidasException;
import com.decodex.br.domain.model.Usuario;
import com.decodex.br.domain.port.in.AuthUseCase;
import com.decodex.br.domain.port.out.PasswordCheckerPort;
import com.decodex.br.domain.port.out.TokenPort;
import com.decodex.br.domain.port.out.UsuarioRepositoryPort;

@Service
public class AuthUseCaseImpl implements AuthUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final TokenPort tokenPort;
    private final PasswordCheckerPort passwordChecker;

    public AuthUseCaseImpl(UsuarioRepositoryPort usuarioRepository,
                          TokenPort tokenPort,
                          PasswordCheckerPort passwordChecker) {
        this.usuarioRepository = usuarioRepository;
        this.tokenPort = tokenPort;
        this.passwordChecker = passwordChecker;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new CredenciaisInvalidasException());

        if (!usuario.authenticate(request.password(), passwordChecker)) {
            throw new CredenciaisInvalidasException();
        }

        String token = tokenPort.generate(usuario);

        UsuarioResponse usuarioResponse = new UsuarioResponse(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRole().name()
        );

        return new LoginResponse(token, usuarioResponse);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}