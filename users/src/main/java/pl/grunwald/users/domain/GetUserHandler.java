package pl.grunwald.users.domain;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import pl.grunwald.users.dto.GetUserHandlerResult;

@Service
public class GetUserHandler {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GetUserHandler.class);

    private final UserInfoProviderPort userInfoProvider;
    private final UserRequestCounterService counterService;
    private final UserInfoCalculatorService calculatorService;

    public GetUserHandler(
            UserInfoProviderPort userInfoProvider,
            UserRequestCounterService counterService,
            UserInfoCalculatorService calculatorService
    ) {
        this.userInfoProvider = userInfoProvider;
        this.counterService = counterService;
        this.calculatorService = calculatorService;
    }

    public GetUserHandlerResult handle(String login) {
        counterService.countRequest(login);

        log.info(String.format("Try to find user with login = %s", login));
        final var userInfo = userInfoProvider.getUserInfo(login);

        return new GetUserHandlerResult(
                userInfo.getId(),
                userInfo.getLogin(),
                userInfo.getName(),
                userInfo.getType(),
                userInfo.getAvatarUrl(),
                userInfo.getCreatedAt(),
                calculatorService.calculate(userInfo)
        );
    }
}
