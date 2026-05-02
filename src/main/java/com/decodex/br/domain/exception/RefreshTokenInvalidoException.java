package com.decodex.br.domain.exception;

public class RefreshTokenInvalidoException extends RuntimeException {

	private static final long serialVersionUID = -5227792711003402826L;

	public RefreshTokenInvalidoException() {
        super("Refresh token inválido ou expirado");
    }
}