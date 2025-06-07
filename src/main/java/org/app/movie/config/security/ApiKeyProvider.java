package org.app.movie.config.security;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class ApiKeyProvider {

    private String apiKey;

    private static final Logger logger = LoggerFactory.getLogger(ApiKeyProvider.class);


    @PostConstruct
    public void generateApiKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[24];
        secureRandom.nextBytes(keyBytes);
        apiKey = Base64.getUrlEncoder().withoutPadding().encodeToString(keyBytes);

        logger.info("Generated API Key: " + apiKey);
    }

    public String getApiKey() {
        return apiKey;
    }
}