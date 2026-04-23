package com.decodex.br.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.decodex.br.adapters.out.persistence.entity.PersonEntity;

public interface PersonJpaRepository
extends JpaRepository<PersonEntity, Long>,
        JpaSpecificationExecutor<PersonEntity> {
}