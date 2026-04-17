package com.decodex.br.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.decodex.br.domain.model.Person;

public interface PersonRepositoryPort {

    Person save(Person person);

    Optional<Person> findById(Long id);

    List<Person> findAll();

    void deleteById(Long id);
}