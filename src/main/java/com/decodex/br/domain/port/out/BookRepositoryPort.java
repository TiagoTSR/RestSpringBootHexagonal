package com.decodex.br.domain.port.out;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.decodex.br.domain.model.Book;

public interface BookRepositoryPort {

    Book save(Book book);

    Optional<Book> findById(Long id);

    Page<Book> findAll(Pageable pageable);

    void deleteById(Long id);
}