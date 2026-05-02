package com.decodex.br.application.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.decodex.br.domain.port.out.RefreshTokenRepositoryPort;

@Component
public class RefreshTokenCleanupJob {

    private static final Logger log = LoggerFactory.getLogger(RefreshTokenCleanupJob.class);

    private final RefreshTokenRepositoryPort refreshTokenRepository;

    public RefreshTokenCleanupJob(RefreshTokenRepositoryPort refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Scheduled(cron = "${app.scheduler.refresh-token-cleanup.cron}")
    @Transactional
    public void limparTokensExpirados() {
        log.info("Iniciando limpeza de refresh tokens expirados...");
        refreshTokenRepository.deleteExpired();
        log.info("Limpeza de refresh tokens concluída.");
    }
}