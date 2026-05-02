package com.decodex.br.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.decodex.br.adapters.in.web.JwtAuthenticationFilter;
import com.decodex.br.adapters.out.persistence.adapter.RefreshTokenRepositoryAdapter;
import com.decodex.br.adapters.out.persistence.adapter.UsuarioRepositoryAdapter;
import com.decodex.br.adapters.out.persistence.repository.RefreshTokenJpaRepository;
import com.decodex.br.adapters.out.persistence.repository.UsuarioJpaRepository;
import com.decodex.br.adapters.out.security.BcryptPasswordCheckerAdapter;
import com.decodex.br.adapters.out.security.JwtTokenAdapter;
import com.decodex.br.application.usecase.AuthUseCaseImpl;
import com.decodex.br.config.property.RestSpring;
import com.decodex.br.domain.port.in.AuthUseCase;
import com.decodex.br.domain.port.in.BookUseCase;
import com.decodex.br.domain.port.in.PersonUseCase;
import com.decodex.br.domain.port.out.BookRepositoryPort;
import com.decodex.br.domain.port.out.PasswordCheckerPort;
import com.decodex.br.domain.port.out.PersonRepositoryPort;
import com.decodex.br.domain.port.out.TokenPort;
import com.decodex.br.domain.service.BookService;
import com.decodex.br.domain.service.PersonService;

@Configuration
@EnableConfigurationProperties(RestSpring.class)
public class BeanConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BookUseCase bookUseCase(BookRepositoryPort bookRepositoryPort) {
        return new BookService(bookRepositoryPort);
    }

    @Bean
    public PersonUseCase personUseCase(PersonRepositoryPort personRepositoryPort) {
        return new PersonService(personRepositoryPort);
    }

    @Bean
    public UsuarioRepositoryAdapter usuarioRepositoryAdapter(UsuarioJpaRepository jpa) {
        return new UsuarioRepositoryAdapter(jpa);
    }

    @Bean
    public TokenPort tokenPort(RestSpring property) {
        return new JwtTokenAdapter(
            property.getJwt().getSecret(),
            property.getJwt().getExpiracaoMinutos()
        );
    }

    @Bean
    public PasswordCheckerPort passwordCheckerPort(PasswordEncoder encoder) {
        return new BcryptPasswordCheckerAdapter(encoder);
    }

    @Bean
    public RefreshTokenRepositoryAdapter refreshTokenRepositoryAdapter(
            RefreshTokenJpaRepository jpa) {
        return new RefreshTokenRepositoryAdapter(jpa);
    }

    @Bean
    public AuthUseCase authUseCase(UsuarioRepositoryAdapter repo,
                                   TokenPort token,
                                   PasswordCheckerPort checker,
                                   RefreshTokenRepositoryAdapter refreshRepo,
                                   RestSpring property) {
        return new AuthUseCaseImpl(
            repo, token, checker, refreshRepo,
            property.getRefreshToken().getExpiracaoDias()
        );
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthUseCase authUseCase,
                                                           TokenPort tokenPort) {
        return new JwtAuthenticationFilter(authUseCase, tokenPort);
    }
}