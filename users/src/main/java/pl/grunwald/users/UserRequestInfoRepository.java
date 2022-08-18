package pl.grunwald.users;

import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRequestInfoRepository extends CrudRepository<UserRequestInfo, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<UserRequestInfo> findByLogin(String login);
}
