package com.decodex.br.adapters.in.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.decodex.br.application.dto.auth.LoginRequest;
import com.decodex.br.application.dto.auth.LoginResponse;
import com.decodex.br.domain.exception.RefreshTokenInvalidoException;
import com.decodex.br.domain.port.in.AuthUseCase;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

 private static final String REFRESH_COOKIE = "refreshToken";
 private static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60;

 private final AuthUseCase authUseCase;
 private final boolean secureCookie;// false em dev, true em prod

 public AuthController(AuthUseCase authUseCase,
                       @Value("${app.cookie.secure:false}") boolean secureCookie) {
     this.authUseCase = authUseCase;
     this.secureCookie = secureCookie;
 }

 @PostMapping("/login")
 public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request,
                                            HttpServletResponse response) {
     LoginResponse loginResponse = authUseCase.login(request);
     String refreshToken = authUseCase.gerarRefreshToken(request.username());
     addRefreshCookie(response, refreshToken);
     return ResponseEntity.ok(loginResponse);
 }

 @PostMapping("/refresh")
 public ResponseEntity<LoginResponse> refresh(HttpServletRequest request,
                                              HttpServletResponse response) {
     String refreshToken = extrairRefreshCookie(request);
     LoginResponse loginResponse = authUseCase.refresh(refreshToken);
     String novoRefreshToken = authUseCase.gerarRefreshToken(loginResponse.usuario().username());
     addRefreshCookie(response, novoRefreshToken);
     return ResponseEntity.ok(loginResponse);
 }

 @PostMapping("/logout")
 public ResponseEntity<Void> logout(HttpServletRequest request,
                                    HttpServletResponse response) {
     String refreshToken = extrairRefreshCookie(request);
     if (refreshToken != null) {
         authUseCase.logout(refreshToken);
     }
     removerRefreshCookie(response);
     return ResponseEntity.noContent().build();
 }

 private void addRefreshCookie(HttpServletResponse response, String value) {
     Cookie cookie = new Cookie(REFRESH_COOKIE, value);
     cookie.setHttpOnly(true);
     cookie.setSecure(secureCookie);
     cookie.setPath("/api/auth");
     cookie.setMaxAge(COOKIE_MAX_AGE);
     cookie.setAttribute("SameSite", "Strict");
     response.addCookie(cookie);
 }

 private void removerRefreshCookie(HttpServletResponse response) {
     Cookie cookie = new Cookie(REFRESH_COOKIE, "");
     cookie.setHttpOnly(true);
     cookie.setSecure(secureCookie);
     cookie.setPath("/api/auth");
     cookie.setMaxAge(0);
     response.addCookie(cookie);
 }

 private String extrairRefreshCookie(HttpServletRequest request) {
     if (request.getCookies() == null) {
         throw new RefreshTokenInvalidoException();
     }
     return Arrays.stream(request.getCookies())
             .filter(c -> REFRESH_COOKIE.equals(c.getName()))
             .map(Cookie::getValue)
             .findFirst()
             .orElseThrow(RefreshTokenInvalidoException::new);
 }
}