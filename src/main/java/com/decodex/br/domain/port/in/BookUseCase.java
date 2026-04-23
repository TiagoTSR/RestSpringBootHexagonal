package com.decodex.br.domain.port.in;

import com.decodex.br.domain.filter.BookFilter;
import com.decodex.br.domain.model.Book;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;

public interface BookUseCase {

	PageResult<Book> findAll(BookFilter filter,PageRequest pageRequest);

    Book findById(Long id);

    Book create(Book book);

    Book update(Long id, Book book);

    void delete(Long id);
}