package com.readff.readff_backend.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.readff.readff_backend.entity.User;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${myclient}")
    private String keycloakClient;

    @Autowired
    private Keycloak keycloak;
    
    public UserRepresentation getUserByUsernameFromKeycloak(String username) {
        List<UserRepresentation> userSearchResult = keycloak.realm(keycloakRealm).users().search(username);
        if (userSearchResult == null) return null;
        return userSearchResult.get(0);
    }

    public User getUserByUsername(String username, String askerUsername) {
        UserRepresentation keycloakUser = getUserByUsernameFromKeycloak(username);
        if (keycloakUser == null) return null;
        User user = new User();

        Map<String, List<String> > userAttributes = keycloakUser.getAttributes();
        if (userAttributes == null) userAttributes = Collections.emptyMap();

        List<String> privateFields = userAttributes.get("privateFields");
        if (privateFields == null) privateFields = Collections.emptyList();

        List<String> friends = userAttributes.get("friends");
        if (friends == null) friends = Collections.emptyList();

        boolean isFriend = friends.contains(askerUsername);

        user.setId(keycloakUser.getId());
        user.setUsername(keycloakUser.getUsername());
        user.setFirstName(privateFields.contains("firstName") ? null : keycloakUser.getFirstName());
        user.setLastName(privateFields.contains("lastName") ? null : keycloakUser.getLastName());
        user.setEmail(privateFields.contains("email") ? null : keycloakUser.getEmail());

        for (String field : privateFields) {
            userAttributes.remove(field);   
        }

        if (!isFriend) {
            List<String> friendsOnlyFields = userAttributes.get("friendsOnlyFields");
            if (friendsOnlyFields != null) {
                for (String field : friendsOnlyFields) {
                    userAttributes.remove(field);   
                }
            }
        }

        user.setAttributes(userAttributes);

        return user;
    }
}
