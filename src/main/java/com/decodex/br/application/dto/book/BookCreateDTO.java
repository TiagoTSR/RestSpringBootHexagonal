package com.decodex.br.application.dto.book;

import java.time.LocalDate;

public record BookCreateDTO(
	    String author,
	    LocalDate launchDate,
	    Double price,
	    String title
	) {}