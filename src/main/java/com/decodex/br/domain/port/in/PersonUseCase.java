package com.decodex.br.domain.port.in;

import java.util.List;
import com.decodex.br.domain.model.Person;

public interface PersonUseCase {

    List<Person> findAll();

    Person findById(Long id);

    Person create(Person person);

    Person update(Long id, Person person);

    void delete(Long id);
}