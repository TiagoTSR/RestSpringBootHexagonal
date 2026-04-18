package com.decodex.br.domain.service;

import java.util.List;

import com.decodex.br.domain.model.Book;
import com.decodex.br.domain.port.in.BookUseCase;
import com.decodex.br.domain.port.out.BookRepositoryPort;

public class BookService implements BookUseCase {

    private final BookRepositoryPort repository;

    public BookService(BookRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book não encontrado: " + id));
    }

    @Override
    public Book create(Book book) {
        return repository.save(book);
    }

    @Override
    public Book update(Long id, Book bookDetails) {
        Book existing = findById(id);

        existing = new Book(
                existing.getId(),
                bookDetails.getAuthor(),
                bookDetails.getLaunchDate(),
                bookDetails.getPrice(),
                bookDetails.getTitle()
        );

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}