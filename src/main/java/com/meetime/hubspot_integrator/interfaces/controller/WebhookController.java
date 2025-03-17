package com.meetime.hubspot_integrator.interfaces.controller;

import com.meetime.hubspot_integrator.application.useCases.ContatoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final ContatoUseCase contatoUseCase;

    @PostMapping("/contato")
    public ResponseEntity<Void> handleWebhook(@RequestBody Map<String, Object> payload) {
        contatoUseCase.processarEventoDeContatoCriado(payload);
        return ResponseEntity.ok().build();
    }
}
