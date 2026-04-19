package com.decodex.br.application.dto.person;

public record PersonCreateDTO (	
	String firstName,
    String lastName,
    String address,
    String gender
) {}