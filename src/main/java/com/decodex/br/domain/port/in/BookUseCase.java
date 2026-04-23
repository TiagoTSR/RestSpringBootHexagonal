package com.decodex.br.domain.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.decodex.br.domain.model.Book;

public interface BookUseCase {

	Page<Book> findAll(Pageable pageable);

    Book findById(Long id);

    Book create(Book book);

    Book update(Long id, Book book);

    void delete(Long id);
}