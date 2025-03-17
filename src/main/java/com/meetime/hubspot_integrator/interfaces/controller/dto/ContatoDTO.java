package com.meetime.hubspot_integrator.interfaces.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContatoDTO(
        Long id,

        @NotBlank(message = "O nome é obrigatório")
        String primeiroNome,
        @NotBlank(message = "O sobrenome é obrigatório")
        String ultimoNome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        String telefone,
        String empresa
) {}
