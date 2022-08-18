package pl.grunwald.users.dto;

import java.time.LocalDateTime;

public class UserInfoResult {

    private final Integer id;
    private final String login;
    private final String name;
    private final String type;
    private final String avatarUrl;
    private final Integer followers;
    private final Integer publicRepos;

    private final LocalDateTime createdAt;


    public UserInfoResult(
            Integer id,
            String login,
            String name,
            String type,
            String avatarUrl,
            Integer followers,
            Integer publicRepos,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.followers = followers;
        this.publicRepos = publicRepos;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getPublicRepos() {
        return publicRepos;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
