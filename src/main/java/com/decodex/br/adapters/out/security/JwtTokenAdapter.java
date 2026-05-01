package com.decodex.br.adapters.out.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import com.decodex.br.domain.model.Usuario;
import com.decodex.br.domain.port.out.TokenPort;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenAdapter implements TokenPort {

    private final String secret;
    private final int expiracaoHoras;

    public JwtTokenAdapter(String secret, int expiracaoHoras) {
        this.secret = secret;
        this.expiracaoHoras = expiracaoHoras;
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
                .expiration(new Date(System.currentTimeMillis() + 3600000L * expiracaoHoras))
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
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}