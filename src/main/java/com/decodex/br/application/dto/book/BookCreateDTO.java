package com.decodex.br.application.dto.book;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookCreateDTO(
		
		@NotBlank(message = "Author não pode estar vazio")
	    String author,
	    
	    @NotNull(message = "Data de lançamento é obrigatória")
	    LocalDate launchDate,
	    
	    @NotNull(message = "Preço é obrigatório")
	    @Positive(message = "Preço deve ser maior que zero")
	    Double price,
	    
	    @NotBlank(message = "Title não pode estar vazio")
	    String title
	) {}