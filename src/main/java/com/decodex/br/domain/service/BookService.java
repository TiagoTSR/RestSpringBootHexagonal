package com.decodex.br.domain.service;

import com.decodex.br.domain.exception.ResourceNotFoundException;
import com.decodex.br.domain.model.Book;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;
import com.decodex.br.domain.port.in.BookUseCase;
import com.decodex.br.domain.port.out.BookRepositoryPort;

public class BookService implements BookUseCase {

    private final BookRepositoryPort repository;

    public BookService(BookRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public PageResult<Book> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book não encontrado: " + id));
    }

    @Override
    public Book create(Book book) {
        return repository.save(book);
    }

    @Override
    public Book update(Long id, Book bookDetails) {
        Book existing = findById(id);

        existing.alterarDataLancamento(bookDetails.getLaunchDate());
        existing.alterarNome(bookDetails.getAuthor(), bookDetails.getTitle());
        existing.alterarPreco(bookDetails.getPrice());
       
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}