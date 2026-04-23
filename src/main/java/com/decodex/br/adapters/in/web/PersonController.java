package com.decodex.br.adapters.in.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.decodex.br.application.dto.person.PersonCreateDTO;
import com.decodex.br.application.dto.person.PersonResponseDTO;
import com.decodex.br.application.dto.person.PersonUpdateDTO;
import com.decodex.br.application.mapper.PersonDTOMapper;
import com.decodex.br.domain.filter.PersonFilter;
import com.decodex.br.domain.model.Gender;
import com.decodex.br.domain.model.Person;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;
import com.decodex.br.domain.port.in.PersonUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonUseCase useCase;

    public PersonController(PersonUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public PageResult<PersonResponseDTO> findAll(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Gender gender,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PersonFilter filter = new PersonFilter(firstName, lastName,address, gender);

        return useCase.findAll(filter, new PageRequest(page, size))
                .map(PersonDTOMapper::toDTO);
    }

    @GetMapping("/{id}")
    public PersonResponseDTO findById(@PathVariable Long id) {
        return PersonDTOMapper.toDTO(useCase.findById(id));
    }

    @PostMapping
    public PersonResponseDTO create(@Valid @RequestBody PersonCreateDTO dto) {
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