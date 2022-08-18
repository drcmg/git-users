package pl.grunwald.users;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRequestInfoRepository extends CrudRepository<UserRequestInfo, Integer> {

    Optional<UserRequestInfo> findByLogin(String login);
}
