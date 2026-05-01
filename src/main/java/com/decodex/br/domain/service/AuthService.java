package com.decodex.br.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.decodex.br.adapters.in.web.exception.GlobalExceptionHandler;
import com.decodex.br.application.dto.auth.LoginRequest;
import com.decodex.br.application.dto.auth.LoginResponse;
import com.decodex.br.application.dto.auth.UsuarioResponse;
import com.decodex.br.domain.exception.CredenciaisInvalidasException;
import com.decodex.br.domain.model.Usuario;
import com.decodex.br.domain.port.in.AuthUseCase;
import com.decodex.br.domain.port.out.PasswordCheckerPort;
import com.decodex.br.domain.port.out.TokenPort;
import com.decodex.br.domain.port.out.UsuarioRepositoryPort;

public class AuthService implements AuthUseCase {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    private final UsuarioRepositoryPort repository;
    private final TokenPort tokenPort;
    private final PasswordCheckerPort passwordChecker;

    public AuthService(
            UsuarioRepositoryPort repository,
            TokenPort tokenPort,
            PasswordCheckerPort passwordChecker
    ) {
        this.repository = repository;
        this.tokenPort = tokenPort;
        this.passwordChecker = passwordChecker;
    }

 // AuthService.java
    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = repository.findByUsername(request.username())
                .orElseThrow(() -> {
                    log.error("Usuário não encontrado: '{}'", request.username());
                    return new CredenciaisInvalidasException();
                });

        log.info("Usuário encontrado: '{}'", usuario.getUsername());
        log.info("Role: '{}'", usuario.getRole());

        boolean senhaOk = usuario.authenticate(request.password(), passwordChecker);
        log.info("Senha válida: {}", senhaOk);

        if (!senhaOk) {
            throw new CredenciaisInvalidasException();
        }

        String token = tokenPort.generate(usuario);
        return new LoginResponse(token,
                new UsuarioResponse(usuario.getId(), usuario.getUsername(), usuario.getRole().name()));
    }
}