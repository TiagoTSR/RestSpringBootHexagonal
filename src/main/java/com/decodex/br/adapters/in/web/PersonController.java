package com.decodex.br.adapters.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.decodex.br.domain.model.Person;
import com.decodex.br.domain.port.in.PersonUseCase;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonUseCase useCase;

    public PersonController(PersonUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<Person> findAll() {
        return useCase.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return useCase.findById(id);
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return useCase.create(person);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable Long id, @RequestBody Person person) {
        return useCase.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        useCase.delete(id);
    }
}