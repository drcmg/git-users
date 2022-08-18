package pl.grunwald.users.domain;

import com.google.common.base.Throwables;
import java.sql.SQLException;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
class UserRequestCounterService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserRequestCounterService.class);

    private final static String UNIQUE_CONSTRAINTS_VIOLATION_SQL_STATE = "23505";
    private final static Integer _1 = 1;

    private final UserRequestInfoRepository repository;
    private final TransactionTemplate transactionTemplate;

    UserRequestCounterService(UserRequestInfoRepository repository, PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    void countRequest(String login) {
        try {
            log.info(String.format("Persist info about request for login = %s", login));
            updateOrCreateRequestCount(login);
        } catch (Exception ex) {
            final var rootCause = Throwables.getRootCause(ex);

            if (uniqueConstraintsViolationOccur(rootCause)) {
                updateRequestCount(login);
            } else {
                throw ex;
            }
        }
    }

    private void updateOrCreateRequestCount(String login) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                final var userRequestInfo = repository.findByLogin(login);
                final var amountOfRequests = getActualAmountOfRequests(userRequestInfo);

                userRequestInfo
                        .ifPresentOrElse(
                                requestInfo -> requestInfo.updateRequestAmount(amountOfRequests),
                                () -> {
                                    final var requestInfo = new UserRequestInfo(login, amountOfRequests);
                                    repository.save(requestInfo);
                                }
                        );
            }
        });
    }

    private void updateRequestCount(String login) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                final var userRequestInfo = repository.findByLogin(login);
                final var amountOfRequests = getActualAmountOfRequests(userRequestInfo);

                userRequestInfo.ifPresent(requestInfo -> requestInfo.updateRequestAmount(amountOfRequests));
            }
        });
    }

    private static Integer getActualAmountOfRequests(Optional<UserRequestInfo> userRequestInfo) {
        return userRequestInfo
                .map(requestInfo -> requestInfo.getRequestAmount() + _1)
                .orElse(_1);
    }

    private static boolean uniqueConstraintsViolationOccur(Throwable rootCause) {
        return rootCause instanceof SQLException &&
                UNIQUE_CONSTRAINTS_VIOLATION_SQL_STATE
                        .equals(((SQLException) rootCause).getSQLState());
    }
}
