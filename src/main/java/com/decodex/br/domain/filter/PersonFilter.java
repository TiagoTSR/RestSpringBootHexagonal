package com.decodex.br.domain.filter;

import com.decodex.br.domain.model.Gender;

public class PersonFilter {

    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;

    public PersonFilter(String firstName, String lastName,String address, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public Gender getGender() { return gender; }
}