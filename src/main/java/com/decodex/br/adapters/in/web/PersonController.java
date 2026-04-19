package com.decodex.br.adapters.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.decodex.br.application.dto.person.PersonCreateDTO;
import com.decodex.br.application.dto.person.PersonResponseDTO;
import com.decodex.br.application.dto.person.PersonUpdateDTO;
import com.decodex.br.application.mapper.PersonDTOMapper;
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
    public List<PersonResponseDTO> findAll() {
        return useCase.findAll()
                .stream()
                .map(PersonDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonResponseDTO findById(@PathVariable Long id) {
        return PersonDTOMapper.toDTO(useCase.findById(id));
    }

    @PostMapping
    public PersonResponseDTO create(@RequestBody PersonCreateDTO dto) {
        Person person = PersonDTOMapper.toDomain(dto);
        Person saved = useCase.create(person);
        return PersonDTOMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public PersonResponseDTO update(@PathVariable Long id,
                                   @RequestBody PersonUpdateDTO dto) {

        Person existing = useCase.findById(id);
        PersonDTOMapper.updateDomain(existing, dto);
        Person updated = useCase.update(id, existing);

        return PersonDTOMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        useCase.delete(id);
    }
}