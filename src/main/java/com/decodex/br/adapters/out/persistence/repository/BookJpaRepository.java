package com.decodex.br.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.decodex.br.adapters.out.persistence.entity.BookEntity;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long>,
										JpaSpecificationExecutor<BookEntity> {
}