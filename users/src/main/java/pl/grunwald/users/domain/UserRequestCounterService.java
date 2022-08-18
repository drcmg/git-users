package pl.grunwald.users.domain;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserRequestCounterService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserRequestCounterService.class);

    private final UserRequestInfoRepository repository;

    public UserRequestCounterService(UserRequestInfoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    void countRequest(String login) {
        log.info(String.format("Persist info about request for login = %s", login));

        final var userRequestInfo = repository.findByLogin(login);
        final var amountOfRequests = userRequestInfo
                .map(requestInfo -> requestInfo.getRequestAmount() + 1)
                .orElse(1);

        if (userRequestInfo.isPresent()) {
            userRequestInfo.get().updateRequestAmount(amountOfRequests);
        } else {
            final var newUserRequestInfo = new UserRequestInfo(login, amountOfRequests);
            repository.save(newUserRequestInfo);
        }
    }
}
