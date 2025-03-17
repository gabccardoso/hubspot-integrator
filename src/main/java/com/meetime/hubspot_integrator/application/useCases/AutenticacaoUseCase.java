package com.meetime.hubspot_integrator.application.useCases;

import com.meetime.hubspot_integrator.application.gateways.AutenticacaoGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AutenticacaoUseCase {

    private final AutenticacaoGateway autenticacaoGateway;

    public AutenticacaoUseCase(AutenticacaoGateway autenticacaoGateway) {
        this.autenticacaoGateway = autenticacaoGateway;
    }

    public String gerarUrlAutorizacao() {
        return autenticacaoGateway.gerarUrlAutorizacao();
    }

    public String trocarCodigoPorToken(String codigoAutorizacao) {
        return autenticacaoGateway.trocarCodigoPorToken(codigoAutorizacao);
    }
}
