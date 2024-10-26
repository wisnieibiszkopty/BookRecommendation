package org.example.bookrecomendationapp.auth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookrecomendationapp.user.User;
import org.example.bookrecomendationapp.user.UserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String username = (String) attributes.get("name");

        userService.findUserByEmail(email).orElseGet(() -> {
            User user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setProvider(userRequest.getClientRegistration().getRegistrationId());
            return userService.saveUser(user);
        });

        return oAuth2User;
    }

}
