package com.decodex.br.adapters.out.persistence.mapper;

import com.decodex.br.adapters.out.persistence.entity.PersonEntity;
import com.decodex.br.domain.model.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class PersonMapper {
	
	public static Person toDomain(PersonEntity entity) {
	    return new Person(
	        entity.getId(),
	        entity.getFirstName(),
	        entity.getLastName(),
	        entity.getAddress(),
	        entity.getGender()
	    );
	}
	
	public static PersonEntity toEntity(Person person) {
	    PersonEntity entity = new PersonEntity();
	    entity.setId(person.getId());
	    entity.setFirstName(person.getFirstName());
	    entity.setLastName(person.getLastName());
	    entity.setAddress(person.getAddress());
	    entity.setGender(person.getGender());
	    return entity;
	}

}
