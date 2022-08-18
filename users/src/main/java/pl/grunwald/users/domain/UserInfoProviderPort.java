package pl.grunwald.users.domain;

import pl.grunwald.users.dto.UserInfoResult;

public interface UserInfoProviderPort {

    UserInfoResult getUserInfo(String login);

}
