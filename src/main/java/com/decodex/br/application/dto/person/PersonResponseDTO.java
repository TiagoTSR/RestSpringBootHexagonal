package com.decodex.br.application.dto.person;

import com.decodex.br.domain.model.Gender;

public record PersonResponseDTO (
	Long id,
	String firstName,
    String lastName,
    String address,
    Gender gender
) {}