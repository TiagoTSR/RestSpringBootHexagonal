package com.decodex.br.application.dto.person;

public record PersonUpdateDTO (	
		String firstName,
	    String lastName,
	    String address,
	    String gender
) {}