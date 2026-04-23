package com.decodex.br.application.dto.book;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookUpdateDTO (
	    String author,
	    LocalDate launchDate,
	    BigDecimal price,
	    String title
	) {}