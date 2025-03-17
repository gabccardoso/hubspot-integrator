package com.meetime.hubspot_integrator.infrastructure.persistence.mapper;

import com.meetime.hubspot_integrator.domain.entity.Contato;
import com.meetime.hubspot_integrator.infrastructure.persistence.ContatoEntity;

public class ContatoEntityMapper {

    public ContatoEntity toEntity(Contato contato){
        return new ContatoEntity(contato.id(), contato.primeiroNome(), contato.ultimoNome(),
                contato.email(), contato.telefone(), contato.empresa(), false, null);
    }
}
