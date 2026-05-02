package com.decodex.br.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class RestSpring {

    private final Jwt jwt = new Jwt();
    private final RefreshToken refreshToken = new RefreshToken();
    private final Scheduler scheduler = new Scheduler();  

    public Jwt getJwt()                       { return jwt; }
    public RefreshToken getRefreshToken()     { return refreshToken; }
    public Scheduler getScheduler()           { return scheduler; }

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

    public static class Scheduler {
        private final RefreshTokenCleanup refreshTokenCleanup = new RefreshTokenCleanup();

        public RefreshTokenCleanup getRefreshTokenCleanup() {
            return refreshTokenCleanup;
        }

        public static class RefreshTokenCleanup {
            private String cron = "0 0 3 * * *"; 

            public String getCron() {
                return cron;
            }

            public void setCron(String cron) {
                this.cron = cron;
            }
        }
    }
}