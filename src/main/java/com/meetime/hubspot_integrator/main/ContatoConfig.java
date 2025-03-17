package com.meetime.hubspot_integrator.main;

import com.meetime.hubspot_integrator.application.gateways.ContatoGateway;
import com.meetime.hubspot_integrator.application.useCases.ContatoUseCase;
import com.meetime.hubspot_integrator.infrastructure.gateways.ContatoRepositoryGateway;
import com.meetime.hubspot_integrator.infrastructure.persistence.ContatoRepository;
import com.meetime.hubspot_integrator.infrastructure.persistence.mapper.ContatoEntityMapper;
import com.meetime.hubspot_integrator.interfaces.controller.dto.ContatoDtoMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ContatoConfig {

    @Bean
    ContatoUseCase contatoUseCase(ContatoGateway contatoGateway){
        return new ContatoUseCase(contatoGateway);
    }

    @Bean
    ContatoGateway contatoGateway(ContatoRepository contatoRepository, ContatoEntityMapper contatoEntityMapper,
                                  RestTemplate restTemplate){
        return new ContatoRepositoryGateway(contatoRepository, contatoEntityMapper, restTemplate);
    }

    @Bean
    ContatoEntityMapper contatoEntityMapper(){
        return new ContatoEntityMapper();
    }

    @Bean
    ContatoDtoMapper contatoDtoMapper(){
        return new ContatoDtoMapper();
    }
}
