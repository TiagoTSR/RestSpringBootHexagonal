package com.decodex.br.domain.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.decodex.br.domain.model.Person;

public interface PersonUseCase {

	Page<Person> findAll(Pageable pageable);

    Person findById(Long id);

    Person create(Person person);

    Person update(Long id, Person person);

    void delete(Long id);
}