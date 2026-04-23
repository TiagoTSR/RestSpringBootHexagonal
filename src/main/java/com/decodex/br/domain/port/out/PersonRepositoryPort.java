package com.decodex.br.domain.port.out;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.decodex.br.domain.model.Person;

public interface PersonRepositoryPort {

    Person save(Person person);

    Optional<Person> findById(Long id);

    Page<Person> findAll(Pageable pageable);

    void deleteById(Long id);
}