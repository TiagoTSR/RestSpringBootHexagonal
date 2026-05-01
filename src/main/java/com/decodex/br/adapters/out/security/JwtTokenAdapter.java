package com.decodex.br.adapters.out.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import com.decodex.br.config.property.RestSpring;
import com.decodex.br.domain.model.Usuario;
import com.decodex.br.domain.port.out.TokenPort;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenAdapter implements TokenPort {

    private static final int HORAS_EXPIRACAO = 1;
    private final RestSpring property;

    public JwtTokenAdapter(RestSpring property) {
        this.property = property;
    }

    @Override
    public String generate(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId());
        claims.put("role", usuario.getRole().name());

        return Jwts.builder()
                .claims(claims)
                .subject(usuario.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000L * HORAS_EXPIRACAO))
                .signWith(signingKey())
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    @Override
    public boolean isValid(String token, String username) {
        try {
            Claims claims = parseClaims(token);
            return claims.getSubject().equals(username)
                    && claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey()).build()
                .parseSignedClaims(token).getPayload();
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(
                property.getJwt().getSecret().getBytes(StandardCharsets.UTF_8));
    }
}