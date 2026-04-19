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
            dto.firstName(),
            dto.firstName(),
            dto.address(),
            Gender.fromString(dto.gender())
        );
    }

    public static void updateDomain(Person person, PersonUpdateDTO dto) {

        if (dto.firstName() != null || dto.firstName() != null) {
            person.alterarNome(
                dto.firstName() != null ? dto.firstName() : person.getFirstName(),
                dto.firstName() != null ? dto.firstName() : person.getLastName()
            );
        }

        if (dto.address() != null) {
            person.alterarEndereco(dto.address());
        }

        if (dto.gender() != null) {
            person.alterarGender(Gender.fromString(dto.gender()));
        }
    }
    
    public static PersonResponseDTO toDTO(Person person) {
	    return new PersonResponseDTO(
	    	person.getId(),
	    	person.getFirstName(),
	    	person.getLastName(),
	    	person.getAddress(),
	    	person.getGender().name()
	    );
	}

}