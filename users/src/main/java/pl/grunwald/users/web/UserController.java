package pl.grunwald.users.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.grunwald.users.UserInfoProviderPort;
import pl.grunwald.users.dto.UserInfoResult;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserInfoProviderPort providerPort;

    public UserController(UserInfoProviderPort providerPort) {
        this.providerPort = providerPort;
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserInfoResult> getByLogin(@PathVariable String login) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(providerPort.getUserInfo(login));
    }
}
