package com.readff.readff_backend.security;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public Keycloak getKeycloakInstance(
        @Value("${keycloak.auth-server-url}")String keycloakUrl,
        @Value("${keycloak.realm}")String keycloakRealm,
        @Value("${myclient}")String keycloakClient) {
        Keycloak keycloak = KeycloakBuilder.builder()
            .serverUrl(keycloakUrl)
            .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
            .realm(keycloakRealm)
            .clientId(keycloakClient)
            .clientSecret("kBxTQrgJ9UtHiBJ4VXZC8Pn6BgoVKPk2")
            .resteasyClient(
                new ResteasyClientBuilder()
                    .connectionPoolSize(10).build()
            ).build();

        keycloak.tokenManager().getAccessToken();
        return keycloak;
    }
}
