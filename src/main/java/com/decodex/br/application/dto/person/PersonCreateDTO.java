package com.decodex.br.application.dto.person;

import com.decodex.br.domain.model.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonCreateDTO (	
		
	@NotBlank(message = "First name não pode estar vazio")
	String firstName,
	
	@NotBlank(message = "Last name não pode estar vazio")
    String lastName,
    
    @NotBlank(message = "Address não pode estar vazio")
    String address,
    
    @NotNull(message = "Gender não pode estar vazio")
	Gender gender
) {}