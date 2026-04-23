package com.decodex.br.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.decodex.br.domain.exception.ResourceNotFoundException;
import com.decodex.br.domain.model.Person;
import com.decodex.br.domain.port.in.PersonUseCase;
import com.decodex.br.domain.port.out.PersonRepositoryPort;

public class PersonService implements PersonUseCase {

    private final PersonRepositoryPort repository;

    public PersonService(PersonRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Page<Person> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Person findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person não encontrada com id: " + id));
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