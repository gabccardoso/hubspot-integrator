package com.meetime.hubspot_integrator.interfaces.controller;

import com.meetime.hubspot_integrator.application.useCases.ContatoUseCase;
import com.meetime.hubspot_integrator.interfaces.controller.dto.ContatoDtoMapper;
import com.meetime.hubspot_integrator.interfaces.controller.dto.ContatoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contato")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoUseCase contatoUseCase;
    private final ContatoDtoMapper contatoDtoMapper;

    @PostMapping
    public ResponseEntity<String> criarNovoContato(@RequestHeader("Authorization") String token,
                                                   @RequestBody ContatoDTO contato) {
         return contatoUseCase.criarNovoContato(contatoDtoMapper.toContato(contato), token);
    }

}
