package com.decodex.br.domain.port.in;

import com.decodex.br.domain.model.Person;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;

public interface PersonUseCase {

	PageResult<Person> findAll(PageRequest pageRequest);

    Person findById(Long id);

    Person create(Person person);

    Person update(Long id, Person person);

    void delete(Long id);
}