package com.nous.rollingrevenue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.nous.rollingrevenue.service.impl.AuditAwareImpl;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditAwareImpl();
	}

}
