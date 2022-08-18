package pl.grunwald.users;

import io.swagger.client.model.PublicUser;
import pl.grunwald.users.dto.UserInfoResult;

public interface UserInfoProviderPort {

    UserInfoResult getUserInfo(String login);

}
