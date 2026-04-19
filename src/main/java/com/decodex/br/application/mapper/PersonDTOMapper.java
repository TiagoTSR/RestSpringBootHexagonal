package com.decodex.br.application.mapper;

import com.decodex.br.application.dto.person.PersonCreateDTO;
import com.decodex.br.application.dto.person.PersonResponseDTO;
import com.decodex.br.application.dto.person.PersonUpdateDTO;
import com.decodex.br.domain.model.Gender;
import com.decodex.br.domain.model.Person;

public class PersonDTOMapper {

    public static Person toDomain(PersonCreateDTO dto) {
        return new Person(
            null,
            dto.getFirstName(),
            dto.getLastName(),
            dto.getAddress(),
            Gender.fromString(dto.getGender())
        );
    }

    public static void updateDomain(Person person, PersonUpdateDTO dto) {

        if (dto.getFirstName() != null || dto.getLastName() != null) {
            person.alterarNome(
                dto.getFirstName() != null ? dto.getFirstName() : person.getFirstName(),
                dto.getLastName() != null ? dto.getLastName() : person.getLastName()
            );
        }

        if (dto.getAddress() != null) {
            person.alterarEndereco(dto.getAddress());
        }

        if (dto.getGender() != null) {
            person.alterarGender(Gender.fromString(dto.getGender()));
        }
    }
    
    public static PersonResponseDTO toDTO(Person person) {
        PersonResponseDTO dto = new PersonResponseDTO();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender().name());
        return dto;
    }
}