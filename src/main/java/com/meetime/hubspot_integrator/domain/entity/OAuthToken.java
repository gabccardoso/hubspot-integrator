package com.meetime.hubspot_integrator.domain.entity;


public record OAuthToken(
        Long id,
        String accessToken,
        String refreshToken,
        Long expiresIn
) {}