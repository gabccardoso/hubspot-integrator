package com.meetime.hubspot_integrator.application.useCases;


import com.meetime.hubspot_integrator.application.gateways.ContatoGateway;
import com.meetime.hubspot_integrator.domain.entity.Contato;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ContatoUseCase {

    private final ContatoGateway contactGateway;

    public ContatoUseCase(ContatoGateway contactGateway) {
        this.contactGateway = contactGateway;
    }

    public void processarEventoDeContatoCriado(Map<String, Object> payload){
        contactGateway.processarEventoDeContatoCriado(payload);
    }

    public ResponseEntity<String> criarNovoContato(Contato contato, String token){
        return contactGateway.criarNovoContato(contato, token);
    }

}
