package com.decodex.br.domain.exception;

public class CredenciaisInvalidasException extends RuntimeException {

	private static final long serialVersionUID = 1771073404347048421L;

	public CredenciaisInvalidasException() {
        super("Usuário ou senha inválidos");
    }
}