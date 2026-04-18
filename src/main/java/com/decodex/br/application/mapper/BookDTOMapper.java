package com.decodex.br.application.mapper;

import com.decodex.br.application.dto.book.BookCreateDTO;
import com.decodex.br.application.dto.book.BookResponseDTO;
import com.decodex.br.application.dto.book.BookUpdateDTO;
import com.decodex.br.domain.model.Book;

public class BookDTOMapper {
	
	public static Book toDomain(BookCreateDTO dto) {
        return new Book(
            null,
            dto.getAuthor(),
            dto.getLaunchDate(),
            dto.getPrice(),
            dto.getTitle()
        );
    }

    public static void updateDomain(Book book, BookUpdateDTO dto) {
        book.alterarNome(dto.getAuthor(), dto.getTitle());
        book.alterarPreco(dto.getPrice());
        book.alterarDataLancamento(dto.getLaunchDate());

    }

    public static BookResponseDTO toDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor());
        dto.setLaunchDate(book.getLaunchDate());
        dto.setPrice(book.getPrice());
        dto.setTitle(book.getTitle());
        return dto;
    }

}
