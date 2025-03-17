package com.meetime.hubspot_integrator.infrastructure.gateways;

import com.meetime.hubspot_integrator.application.gateways.AutenticacaoGateway;
import com.meetime.hubspot_integrator.infrastructure.persistence.TokenEntity;
import com.meetime.hubspot_integrator.infrastructure.persistence.TokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AutenticacaoRepositoryGateway implements AutenticacaoGateway {

    @Value("${hubspot.client.id}")
    private String clientId;

    @Value("${hubspot.client.secret}")
    private String clientSecret;

    @Value("${hubspot.redirect.uri}")
    private String redirectUri;

    private final RestTemplate restTemplate;
    private final TokenRepository tokenRepository;

    private static final String TOKEN_URL = "https://api.hubapi.com/oauth/v1/token";


    public AutenticacaoRepositoryGateway(RestTemplate restTemplate, TokenRepository tokenRepository) {
        this.restTemplate = restTemplate;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String gerarUrlAutorizacao() {
        String scopes = "crm.objects.contacts.read crm.objects.contacts.write";
        return String.format(
                "https://app.hubspot.com/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s&response_type=code",
                clientId, redirectUri, URLEncoder.encode(scopes, StandardCharsets.UTF_8)
        );
    }

    @Override
    public String trocarCodigoPorToken(String codigo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("code", codigo);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(TOKEN_URL, request, Map.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            TokenEntity token = new TokenEntity();
            token.setAccessToken(response.getBody().get("access_token").toString());
            token.setRefreshToken(response.getBody().get("refresh_token").toString());
            token.setExpiresIn(Long.parseLong(response.getBody().get("expires_in").toString()));
            token.setCreatedAt(System.currentTimeMillis());

            tokenRepository.save(token);

            return token.getAccessToken();
        } else {
            throw new RuntimeException("Erro ao obter access token");
        }
    }
}
