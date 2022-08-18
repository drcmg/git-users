package pl.grunwald.users.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.grunwald.users.GetUserHandler;
import pl.grunwald.users.dto.GetUserHandlerResult;

@RequestMapping("/users")
@RestController
public class UserController {

    private final GetUserHandler getUserHandler;

    public UserController(GetUserHandler getUserHandler) {
        this.getUserHandler = getUserHandler;
    }

    @GetMapping("/{login}")
    public ResponseEntity<GetUserHandlerResult> getByLogin(@PathVariable String login) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getUserHandler.handle(login));
    }
}
