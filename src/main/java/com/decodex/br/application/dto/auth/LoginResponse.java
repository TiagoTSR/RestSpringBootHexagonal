package com.decodex.br.application.dto.auth;

public record LoginResponse(String accessToken, UsuarioResponse usuario) {}