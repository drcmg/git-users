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


    private UserInfoResult(
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


    public static UserInfoResultBuilder builder() {
        return new UserInfoResultBuilder();
    }

    public static class UserInfoResultBuilder {

        private Integer id;
        private String login;
        private String name;
        private String type;
        private String avatarUrl;
        private Integer followers;
        private Integer publicRepos;
        private LocalDateTime createdAt;


        UserInfoResultBuilder() {
        }

        public UserInfoResultBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserInfoResultBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserInfoResultBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserInfoResultBuilder type(String type) {
            this.type = type;
            return this;
        }

        public UserInfoResultBuilder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public UserInfoResultBuilder followers(Integer followers) {
            this.followers = followers;
            return this;
        }

        public UserInfoResultBuilder publicRepos(Integer publicRepos) {
            this.publicRepos = publicRepos;
            return this;
        }

        public UserInfoResultBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserInfoResult build() {
            return new UserInfoResult(
                    this.id,
                    this.login,
                    this.name,
                    this.type,
                    this.avatarUrl,
                    this.followers,
                    this.publicRepos,
                    this.createdAt
            );
        }
    }
}
