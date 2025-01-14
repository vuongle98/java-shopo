package org.vuong.shopo.shared.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.vuong.shopo.application.dto.TokenDto;
import org.vuong.shopo.infrastructure.integration.HttpClient;
import org.vuong.shopo.infrastructure.integration.RequestContext;
import org.vuong.shopo.shared.exceptions.TokenNotFoundException;

import java.time.Instant;

@Service
public class TokenService {

    private final HttpClient httpClient;
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;
    @Value("${shopo.client-id}")
    private String clientId;
    @Value("${shopo.client-secret}")
    private String clientSecret;
    private String cachedToken; // Cached service token
    private Instant tokenExpiry; // Token expiry time

    public TokenService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getServiceToken() {
        if (cachedToken == null || Instant.now().isAfter(tokenExpiry)) {
            refreshToken();
        }
        return cachedToken;
    }

    private void refreshToken() {
        String tokenUri = issuerUri + "/token/";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);

        RequestContext tokenRequestContext = new RequestContext();
        tokenRequestContext.setBodyType(MediaType.APPLICATION_FORM_URLENCODED);

        ResponseEntity<TokenDto> tokenResponse = httpClient.execute(
                tokenUri,
                HttpMethod.POST,
                tokenRequestContext,
                null,
                body,
                TokenDto.class
        );

        if (tokenResponse.hasBody() && tokenResponse.getBody() != null) {
            TokenDto tokenDto = tokenResponse.getBody();
            cachedToken = tokenDto.getAccessToken();
            tokenExpiry = Instant.now().plusSeconds(tokenDto.getExpiresIn() - 60); // Subtract 60 seconds for buffer
        } else {
            throw new TokenNotFoundException("Failed to fetch service token");
        }
    }
}
