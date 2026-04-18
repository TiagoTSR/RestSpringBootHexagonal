package com.decodex.br.domain.port.in;

import java.util.List;

import com.decodex.br.domain.model.Book;

public interface BookUseCase {

    List<Book> findAll();

    Book findById(Long id);

    Book create(Book book);

    Book update(Long id, Book book);

    void delete(Long id);
}