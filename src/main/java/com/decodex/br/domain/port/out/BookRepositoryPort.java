package com.decodex.br.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.decodex.br.domain.model.Book;

public interface BookRepositoryPort {

    Book save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll();

    void deleteById(Long id);
}