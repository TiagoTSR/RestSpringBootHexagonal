package com.decodex.br.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class RestSpring {

    private final Jwt jwt = new Jwt();

    public Jwt getJwt() { return jwt; }

    public static class Jwt {
        private String secret;
        private int expiracaoHoras = 1;

        public String getSecret()            { return secret; }
        public void setSecret(String s)      { this.secret = s; }
        public int getExpiracaoHoras()       { return expiracaoHoras; }
        public void setExpiracaoHoras(int h) { this.expiracaoHoras = h; }
    }
}