package com.meetime.hubspot_integrator.infrastructure.gateways;

import com.meetime.hubspot_integrator.application.gateways.ContatoGateway;
import com.meetime.hubspot_integrator.domain.entity.Contato;
import com.meetime.hubspot_integrator.infrastructure.persistence.ContatoEntity;
import com.meetime.hubspot_integrator.infrastructure.persistence.ContatoRepository;
import com.meetime.hubspot_integrator.infrastructure.persistence.mapper.ContatoEntityMapper;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class ContatoRepositoryGateway implements ContatoGateway {

    private final ContatoRepository contatoRepository;
    private final ContatoEntityMapper contatoEntityMapper;
    private final RestTemplate restTemplate;

    public ContatoRepositoryGateway(ContatoRepository contatoRepository, ContatoEntityMapper contatoEntityMapper, RestTemplate restTemplate) {
        this.contatoRepository = contatoRepository;
        this.contatoEntityMapper = contatoEntityMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public void processarEventoDeContatoCriado(Map<String, Object> payload) {
        try {
            ContatoEntity contatoEntity = new ContatoEntity();
            contatoEntity.setEmail((String) payload.get("email"));
            contatoEntity.setPrimeiroNome ((String) payload.get("firstname"));
            contatoEntity.setUltimoNome ((String) payload.get("lastname"));
            contatoEntity.setTelefone ((String) payload.get("phone"));
            contatoEntity.setEmpresa ((String) payload.get("phone"));

            contatoRepository.save(contatoEntity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar webhook");
        }
    }

    @Override
    public ResponseEntity<String> criarNovoContato(Contato contato, String token) {
        ContatoEntity contatoEntity = contatoEntityMapper.toEntity(contato);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> properties = new HashMap<>();
        properties.put("email", contatoEntity.getEmail());
        properties.put("firstname", contatoEntity.getPrimeiroNome());
        properties.put("lastname", contatoEntity.getUltimoNome());
        properties.put("phone", contatoEntity.getTelefone());
        properties.put("company", contatoEntity.getEmpresa());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("properties", properties);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://api.hubapi.com/crm/v3/objects/contacts",
                    HttpMethod.POST, request, String.class);

            if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
                contatoEntity.setAtivo(false);
                contatoRepository.save(contatoEntity);
                return ResponseEntity.ok("Contato criado com sucesso!");
            } else{
                throw new RuntimeException("Falha ao criar contato no HubSpot: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Erro ao criar contato: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao processar requisição");
        }
    }
}
