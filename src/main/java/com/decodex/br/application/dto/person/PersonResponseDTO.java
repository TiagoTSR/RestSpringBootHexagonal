package com.decodex.br.application.dto.person;

public record PersonResponseDTO (
	Long id,
	String firstName,
    String lastName,
    String address,
    String gender
) {}