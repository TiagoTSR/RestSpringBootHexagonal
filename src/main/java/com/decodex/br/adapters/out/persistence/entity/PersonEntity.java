package com.decodex.br.adapters.out.persistence.entity;

import com.decodex.br.domain.model.Gender;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class PersonEntity {
	
	private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
