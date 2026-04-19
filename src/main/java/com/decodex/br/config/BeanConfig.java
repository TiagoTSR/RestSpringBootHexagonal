package com.decodex.br.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.decodex.br.domain.port.in.BookUseCase;
import com.decodex.br.domain.port.in.PersonUseCase;
import com.decodex.br.domain.port.out.BookRepositoryPort;
import com.decodex.br.domain.port.out.PersonRepositoryPort;
import com.decodex.br.domain.service.BookService;
import com.decodex.br.domain.service.PersonService;

@Configuration
public class BeanConfig {

    @Bean
    public BookUseCase bookUseCase(BookRepositoryPort bookRepositoryPort) {
        return new BookService(bookRepositoryPort);
    }

    @Bean
    public PersonUseCase personUseCase(PersonRepositoryPort personRepositoryPort) {
        return new PersonService(personRepositoryPort);
    }
}