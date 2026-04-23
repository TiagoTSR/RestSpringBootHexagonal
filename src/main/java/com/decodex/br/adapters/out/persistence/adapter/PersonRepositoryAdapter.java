package com.decodex.br.adapters.out.persistence.adapter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.decodex.br.adapters.out.persistence.entity.PersonEntity;
import com.decodex.br.adapters.out.persistence.mapper.PersonMapper;
import com.decodex.br.adapters.out.persistence.repository.PersonJpaRepository;
import com.decodex.br.domain.model.Person;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;
import com.decodex.br.domain.port.out.PersonRepositoryPort;

@Component
public class PersonRepositoryAdapter implements PersonRepositoryPort {

    private final PersonJpaRepository repository;
    private final PersonMapper mapper;

    public PersonRepositoryAdapter(PersonJpaRepository repository) {
        this.repository = repository;
        this.mapper = new PersonMapper();
    }

    @Override
    public Person save(Person person) {
        return mapper.toDomain(
            repository.save(mapper.toEntity(person))
        );
    }

    @Override
    public Optional<Person> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public PageResult<Person> findAll(PageRequest request) {

        Page<PersonEntity> page = repository.findAll(
            org.springframework.data.domain.PageRequest.of(
                request.getPage(),
                request.getSize()
            )
        );

        return new PageResult<>(
            page.getContent().stream().map(mapper::toDomain).toList(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages()
        );
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}