package com.decodex.br.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.decodex.br.adapters.in.web.JwtAuthenticationFilter;
import com.decodex.br.adapters.out.persistence.adapter.UsuarioRepositoryAdapter;
import com.decodex.br.adapters.out.persistence.repository.UsuarioJpaRepository;
import com.decodex.br.adapters.out.security.BcryptPasswordCheckerAdapter;
import com.decodex.br.adapters.out.security.JwtTokenAdapter;
import com.decodex.br.config.property.RestSpring;
import com.decodex.br.domain.model.Usuario;
import com.decodex.br.domain.port.in.AuthUseCase;
import com.decodex.br.domain.port.in.BookUseCase;
import com.decodex.br.domain.port.in.PersonUseCase;
import com.decodex.br.domain.port.out.BookRepositoryPort;
import com.decodex.br.domain.port.out.PasswordCheckerPort;
import com.decodex.br.domain.port.out.PersonRepositoryPort;
import com.decodex.br.domain.port.out.TokenPort;
import com.decodex.br.domain.service.AuthService;
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
        return new JwtTokenAdapter(property);
    }

    @Bean
    public PasswordCheckerPort passwordCheckerPort(PasswordEncoder encoder) {
        return new BcryptPasswordCheckerAdapter(encoder);
    }

    @Bean
    public AuthUseCase authUseCase(UsuarioRepositoryAdapter repo,
                                   TokenPort token,
                                   PasswordCheckerPort checker) {
        return new AuthService(repo, token, checker);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(TokenPort tokenPort,
                                                           UsuarioRepositoryAdapter repo) {
        return new JwtAuthenticationFilter(tokenPort, repo);
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepositoryAdapter adapter) {
        return username -> {
            Usuario usuario = adapter.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            String hash = adapter.findPasswordHashByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            return User.withUsername(usuario.getUsername())
                    .password(hash)
                    .roles(usuario.getRole().name())
                    .build();
        };
    }
}