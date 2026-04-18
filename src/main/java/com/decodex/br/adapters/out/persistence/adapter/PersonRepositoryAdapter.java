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

    public PersonRepositoryAdapter(PersonJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        return PersonMapper.toDomain(
            repository.save(PersonMapper.toEntity(person))
        );
    }

    @Override
    public Optional<Person> findById(Long id) {
        return repository.findById(id)
                .map(PersonMapper::toDomain);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll()
                .stream()
                .map(PersonMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
