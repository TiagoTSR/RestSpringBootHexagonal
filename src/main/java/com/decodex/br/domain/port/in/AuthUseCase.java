package com.decodex.br.domain.port.in;

import com.decodex.br.application.dto.auth.LoginRequest;
import com.decodex.br.application.dto.auth.LoginResponse;

public interface AuthUseCase {
    LoginResponse login(LoginRequest request);
}