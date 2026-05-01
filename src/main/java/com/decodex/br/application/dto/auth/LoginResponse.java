package com.decodex.br.application.dto.auth;

public record LoginResponse(String token, UsuarioResponse usuario) {}