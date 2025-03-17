package com.meetime.hubspot_integrator.domain.entity;


public record Contato(
        Long id,
        String primeiroNome,
        String ultimoNome,
        String email,
        String telefone,
        String empresa
) {}
