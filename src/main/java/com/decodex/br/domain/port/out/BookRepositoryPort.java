package com.decodex.br.domain.port.out;

import java.util.Optional;

import com.decodex.br.domain.filter.BookFilter;
import com.decodex.br.domain.model.Book;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;

public interface BookRepositoryPort {

    Book save(Book book);

    Optional<Book> findById(Long id);

    PageResult<Book> findAll(BookFilter filter,PageRequest pageRequest);

    void deleteById(Long id);
}