package com.meetime.hubspot_integrator.main;

import com.meetime.hubspot_integrator.application.gateways.AutenticacaoGateway;
import com.meetime.hubspot_integrator.application.useCases.AutenticacaoUseCase;
import com.meetime.hubspot_integrator.infrastructure.gateways.AutenticacaoRepositoryGateway;
import com.meetime.hubspot_integrator.infrastructure.persistence.TokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AutorizationConfig {

    @Bean
    AutenticacaoUseCase autenticacaoUseCase(AutenticacaoGateway autenticacaoGateway){
        return new AutenticacaoUseCase(autenticacaoGateway);
    }

    @Bean
    AutenticacaoGateway autenticacaoGateway(RestTemplate restTemplate, TokenRepository tokenRepository){
        return new AutenticacaoRepositoryGateway(restTemplate, tokenRepository);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
