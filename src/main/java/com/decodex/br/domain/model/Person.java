package com.decodex.br.domain.model;

import java.util.Objects;

public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;

    public Person(Long id, String firstName, String lastName, String address, Gender gender) {
        this.id = id;
        this.firstName = validarTexto(firstName, "First name");
        this.lastName = validarTexto(lastName, "Last name");
        this.address = validarTexto(address, "Address");
        this.gender = Objects.requireNonNull(gender, "Gender não pode ser nulo");
    }

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " não pode ser vazio");
        }
        return valor;
    }

    public void alterarEndereco(String novoEndereco) {
        this.address = validarTexto(novoEndereco, "Address");
    }

    public void alterarNome(String primeiroNome, String sobrenome) {
        this.firstName = validarTexto(primeiroNome, "First name");
        this.lastName = validarTexto(sobrenome, "Last name");
    }
    
    public void alterarGender(Gender novoGender) {
        this.gender = Objects.requireNonNull(novoGender, "Gender não pode ser nulo");
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public Gender getGender() { return gender; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}