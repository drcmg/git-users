package pl.grunwald.users.integration;

import io.swagger.client.model.PublicUser;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.grunwald.users.UserInfoProviderPort;
import pl.grunwald.users.dto.UserInfoResult;

@Service
public class UserInfoProviderAdapter implements UserInfoProviderPort {

    private final RestTemplate restTemplate;

    @Value("${user.info.uri}")
    private String userInfoUri;

    public UserInfoProviderAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserInfoResult getUserInfo(String userLogin) {
        Map<String, String> login = new HashMap<>();
        login.put("login", userLogin);

        final var uri = UriComponentsBuilder
                .fromUriString(userInfoUri)
                .build(login);

        final var exchange = restTemplate.exchange(uri, HttpMethod.GET, null, PublicUser.class);

        return Optional
                .of(exchange)
                .map(HttpEntity::getBody)
                .map(this::mapToUserInfoDto)
                .orElse(null);
    }

    private UserInfoResult mapToUserInfoDto(PublicUser user) {
        return new UserInfoResult(
                user.getId(),
                user.getLogin(),
                user.getName(),
                user.getType(),
                user.getAvatarUrl(),
                user.getFollowers(),
                user.getPublicRepos(),
                toLocalDateTime(user.getCreatedAt())
        );
    }

    private static LocalDateTime toLocalDateTime(Date date) {
        return Optional.ofNullable(date)
                .map(d -> LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()))
                .orElse(null);
    }
}
