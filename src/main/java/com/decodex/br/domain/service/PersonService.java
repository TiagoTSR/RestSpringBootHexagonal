package com.decodex.br.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.decodex.br.domain.model.Person;
import com.decodex.br.domain.port.in.PersonUseCase;
import com.decodex.br.domain.port.out.PersonRepositoryPort;

@Service
public class PersonService implements PersonUseCase {

    private final PersonRepositoryPort repository;

    public PersonService(PersonRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Person findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person não encontrada com id: " + id));
    }

    @Override
    public Person create(Person person) {
        return repository.save(person);
    }

    @Override
    public Person update(Long id, Person personDetails) {
        Person existing = findById(id);

        existing.alterarNome(
                personDetails.getFirstName(),
                personDetails.getLastName()
        );

        existing.alterarEndereco(personDetails.getAddress());

        if (personDetails.getGender() != null) {
            existing.alterarGender(personDetails.getGender());
        }

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}