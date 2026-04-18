package com.decodex.br.adapters.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.decodex.br.adapters.out.persistence.mapper.BookMapper;
import com.decodex.br.adapters.out.persistence.repository.BookJpaRepository;
import com.decodex.br.domain.model.Book;
import com.decodex.br.domain.port.out.BookRepositoryPort;


@Component
public class BookRepositoryAdapter implements BookRepositoryPort {

    private final BookJpaRepository repository;

    public BookRepositoryAdapter(BookJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return BookMapper.toDomain(
            repository.save(BookMapper.toEntity(book))
        );
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id)
                .map(BookMapper::toDomain);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll()
                .stream()
                .map(BookMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}