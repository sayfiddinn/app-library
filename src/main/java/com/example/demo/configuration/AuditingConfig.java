package com.example.demo.configuration;

import com.example.demo.audit.SpringSecurityAuditAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return new SpringSecurityAuditAwareImpl();
    }
}