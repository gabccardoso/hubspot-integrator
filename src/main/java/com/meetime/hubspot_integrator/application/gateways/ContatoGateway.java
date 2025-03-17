package com.meetime.hubspot_integrator.application.gateways;

import com.meetime.hubspot_integrator.domain.entity.Contato;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ContatoGateway {

    public void processarEventoDeContatoCriado(Map<String, Object> payload);
    public ResponseEntity<String> criarNovoContato(Contato contato, String token);
}
