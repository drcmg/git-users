package pl.grunwald.users.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GetUserHandlerResult {

    private final Integer id;
    private final String login;
    private final String name;
    private final String type;
    private final String avatarUrl;
    private final LocalDateTime createdAt;
    private final BigDecimal calculations;

    public GetUserHandlerResult(
            Integer id,
            String login,
            String name,
            String type,
            String avatarUrl,
            LocalDateTime createdAt,
            BigDecimal calculations
    ) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = calculations;
    }

    public Integer getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public BigDecimal getCalculations() {
        return this.calculations;
    }
}
