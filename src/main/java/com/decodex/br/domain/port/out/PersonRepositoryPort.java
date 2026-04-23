package com.decodex.br.domain.port.out;

import java.util.Optional;

import com.decodex.br.domain.filter.PersonFilter;
import com.decodex.br.domain.model.Person;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;

public interface PersonRepositoryPort {

    Person save(Person person);

    Optional<Person> findById(Long id);

    PageResult<Person> findAll(PersonFilter filter,PageRequest pageRequest);

    void deleteById(Long id);
}