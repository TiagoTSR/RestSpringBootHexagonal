package com.decodex.br.application.mapper;

import com.decodex.br.application.dto.book.BookCreateDTO;
import com.decodex.br.application.dto.book.BookResponseDTO;
import com.decodex.br.application.dto.book.BookUpdateDTO;
import com.decodex.br.domain.model.Book;

public class BookDTOMapper {
	
	public static Book toDomain(BookCreateDTO dto) {
        return new Book(
            null,
            dto.author(),
            dto.launchDate(),
            dto.price(),
            dto.title()
        );
    }

	public static void updateDomain(Book book, BookUpdateDTO dto) {

	    if (dto.author() != null || dto.title() != null) {
	        book.alterarNome(
	            dto.author() != null ? dto.author() : book.getAuthor(),
	            dto.title() != null ? dto.title() : book.getTitle()
	        );
	    }

	    if (dto.price() != null) {
	        book.alterarPreco(dto.price());
	    }

	    if (dto.launchDate() != null) {
	        book.alterarDataLancamento(dto.launchDate());
	    }
	}

	public static BookResponseDTO toDTO(Book book) {
	    return new BookResponseDTO(
	        book.getId(),
	        book.getAuthor(),
	        book.getLaunchDate(),
	        book.getPrice(),
	        book.getTitle()
	    );
	}

}
