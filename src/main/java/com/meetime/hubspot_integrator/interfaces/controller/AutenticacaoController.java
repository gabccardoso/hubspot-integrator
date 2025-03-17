package com.meetime.hubspot_integrator.interfaces.controller;

import com.meetime.hubspot_integrator.application.useCases.AutenticacaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoUseCase autenticacaoUseCase;

    @GetMapping("/retorno")
    public ResponseEntity<String> processaRetorno(@RequestParam String code) {
        String token = autenticacaoUseCase.trocarCodigoPorToken(code);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/autoriza")
    public ResponseEntity<String> gerarUrlAutorizacao() {
        String authUrl = autenticacaoUseCase.gerarUrlAutorizacao();
        return ResponseEntity.ok(authUrl);
    }
}
