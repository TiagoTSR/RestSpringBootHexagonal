package com.decodex.br.application.dto.person;

import com.decodex.br.domain.model.Gender;

import jakarta.validation.constraints.NotBlank;

public record PersonCreateDTO (	
		
	@NotBlank(message = "First name não pode estar vazio")
	String firstName,
	
	@NotBlank(message = "Last name não pode estar vazio")
    String lastName,
    
    @NotBlank(message = "Address não pode estar vazio")
    String address,
    
    @NotBlank(message = "Gender não pode estar vazio")
	Gender gender
) {}