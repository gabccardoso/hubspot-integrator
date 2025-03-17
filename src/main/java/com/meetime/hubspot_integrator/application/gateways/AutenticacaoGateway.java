package com.meetime.hubspot_integrator.application.gateways;


public interface AutenticacaoGateway {

    public String gerarUrlAutorizacao();
    public String trocarCodigoPorToken(String codigoAutorizacao);
}
