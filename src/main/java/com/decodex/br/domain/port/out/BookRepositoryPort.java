package com.decodex.br.domain.port.out;

import java.util.Optional;

import com.decodex.br.domain.model.Book;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;

public interface BookRepositoryPort {

    Book save(Book book);

    Optional<Book> findById(Long id);

    PageResult<Book> findAll(PageRequest pageRequest);

    void deleteById(Long id);
}