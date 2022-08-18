package pl.grunwald.users;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import pl.grunwald.users.dto.GetUserHandlerResult;
import pl.grunwald.users.dto.UserInfoResult;

@Service
public class GetUserHandler {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GetUserHandler.class);
    private final static BigDecimal _6 = new BigDecimal("6");
    private final static BigDecimal _2 = new BigDecimal("2");

    private final UserInfoProviderPort providerPort;

    public GetUserHandler(UserInfoProviderPort providerPort) {
        this.providerPort = providerPort;
    }

    public GetUserHandlerResult handle(String login) {
        log.info(String.format("Try to find user with login %s", login));

        final var userInfo = providerPort.getUserInfo(login);

        return new GetUserHandlerResult(
                userInfo.getId(),
                userInfo.getLogin(),
                userInfo.getName(),
                userInfo.getType(),
                userInfo.getAvatarUrl(),
                userInfo.getCreatedAt(),
                calculate(userInfo)
        );
    }

    BigDecimal calculate(UserInfoResult userInfo) {
        if (userInfo.getFollowers() == null || userInfo.getFollowers().equals(0)) {
            log.info("For calculation followers amount cannot be empty or 0.");
            return null;
        }

        if (userInfo.getPublicRepos() == null) {
            log.info("For calculation publicRepos amount cannot be empty.");
            return null;
        }

        final var followersAmount = new BigDecimal(String.valueOf(userInfo.getFollowers()));
        final var publicReposAmount = new BigDecimal(String.valueOf(userInfo.getPublicRepos()));

        return _6.divide(followersAmount, 10, RoundingMode.HALF_UP)
                .multiply(_2.add(publicReposAmount));
    }
}
