package com.decodex.br.domain.model;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Gender não pode ser nulo");
        }

        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Gender inválido: " + value);
        }
    }
}