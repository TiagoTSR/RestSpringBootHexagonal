package com.decodex.br.application.dto.book;

import java.time.LocalDate;

public record BookUpdateDTO (
	    String author,
	    LocalDate launchDate,
	    Double price,
	    String title
	) {}