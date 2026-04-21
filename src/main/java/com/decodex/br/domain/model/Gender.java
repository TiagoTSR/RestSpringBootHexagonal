package com.decodex.br.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE, FEMALE;

    @JsonCreator
    public static Gender fromString(String value) {
        if (value == null) return null;
        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Gender inválido. Valores aceitos: MALE, FEMALE");
        }
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}