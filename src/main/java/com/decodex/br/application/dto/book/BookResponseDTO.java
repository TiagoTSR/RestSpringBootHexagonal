package com.decodex.br.application.dto.book;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookResponseDTO (
    Long id,
	String author,
    LocalDate launchDate,
    BigDecimal price,
    String title
) {}