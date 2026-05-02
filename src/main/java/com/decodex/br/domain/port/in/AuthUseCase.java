package com.decodex.br.domain.port.in;

import java.util.Optional;
import com.decodex.br.application.dto.auth.LoginRequest;
import com.decodex.br.application.dto.auth.LoginResponse;
import com.decodex.br.domain.model.Usuario;

public interface AuthUseCase {
    LoginResponse login(LoginRequest request);
    LoginResponse refresh(String refreshToken);
    void logout(String refreshToken);
    Optional<Usuario> buscarUsuarioPorUsername(String username);
	String gerarRefreshToken(String username);
}