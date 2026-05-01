package com.decodex.br.adapters.in.web;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.decodex.br.domain.port.out.TokenPort;
import com.decodex.br.domain.port.out.UsuarioRepositoryPort;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenPort tokenPort;
    private final UsuarioRepositoryPort usuarioRepository;

    public JwtAuthenticationFilter(TokenPort tokenPort,
                                   UsuarioRepositoryPort usuarioRepository) {
        this.tokenPort = tokenPort;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws ServletException, IOException {

        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        String jwt = header.substring(7);
        String username;
        try {
            username = tokenPort.extractUsername(jwt);
        } catch (Exception ex) {
            chain.doFilter(req, res);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            usuarioRepository.findByUsername(username).ifPresent(usuario -> {
                if (tokenPort.isValid(jwt, usuario.getUsername())) {
                    var auth = new UsernamePasswordAuthenticationToken(
                            usuario.getUsername(),
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()))
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            });
        }

        chain.doFilter(req, res);
    }
}