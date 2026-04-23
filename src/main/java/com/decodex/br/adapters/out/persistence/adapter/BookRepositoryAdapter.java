package com.decodex.br.adapters.out.persistence.adapter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.decodex.br.adapters.out.persistence.mapper.BookMapper;
import com.decodex.br.adapters.out.persistence.repository.BookJpaRepository;
import com.decodex.br.domain.model.Book;
import com.decodex.br.domain.port.out.BookRepositoryPort;

@Component
public class BookRepositoryAdapter implements BookRepositoryPort {

    private final BookJpaRepository repository;
    private final BookMapper mapper;

    public BookRepositoryAdapter(BookJpaRepository repository) {
        this.repository = repository;
        this.mapper = new BookMapper();
    }

    @Override
    public Book save(Book book) {
        return mapper.toDomain(
            repository.save(mapper.toEntity(book))
        );
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}