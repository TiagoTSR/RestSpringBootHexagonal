package com.decodex.br.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class RestSpring {

    private final Jwt jwt = new Jwt();
    private final RefreshToken refreshToken = new RefreshToken();

    public Jwt getJwt()                   { return jwt; }
    public RefreshToken getRefreshToken() { return refreshToken; }

    public static class Jwt {
        private String secret;
        private int expiracaoMinutos = 15;

        public String getSecret()                { return secret; }
        public void setSecret(String s)          { this.secret = s; }
        public int getExpiracaoMinutos()         { return expiracaoMinutos; }
        public void setExpiracaoMinutos(int m)   { this.expiracaoMinutos = m; }
    }

    public static class RefreshToken {
        private long expiracaoDias = 7;

        public long getExpiracaoDias()          { return expiracaoDias; }
        public void setExpiracaoDias(long d)    { this.expiracaoDias = d; }
    }
}