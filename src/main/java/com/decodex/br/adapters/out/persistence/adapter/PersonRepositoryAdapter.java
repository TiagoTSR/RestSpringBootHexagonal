package com.decodex.br.adapters.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.decodex.br.adapters.out.persistence.mapper.PersonMapper;
import com.decodex.br.adapters.out.persistence.repository.PersonJpaRepository;
import com.decodex.br.domain.model.Person;
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
    public List<Person> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}