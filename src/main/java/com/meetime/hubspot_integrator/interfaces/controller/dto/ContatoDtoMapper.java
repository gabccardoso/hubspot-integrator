package com.meetime.hubspot_integrator.interfaces.controller.dto;

import com.meetime.hubspot_integrator.domain.entity.Contato;

public class ContatoDtoMapper {

    public Contato toContato(ContatoDTO contatoDTO){
        return new Contato(contatoDTO.id(), contatoDTO.primeiroNome(), contatoDTO.ultimoNome(), contatoDTO.email(),
                contatoDTO.telefone(), contatoDTO.empresa());
    }
}
