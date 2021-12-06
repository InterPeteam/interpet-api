package com.interteam.interpet.api.controller.auth;

import com.interteam.interpet.api.model.User;
import com.interteam.interpet.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

// TODO: implement mappers & potential validators in endpoints

@RestController
class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public TokenTransfer login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        userService.validateLogin(email, password);

        return userService.login(email, password);
    }


    //TODO weryfikować miasto i role czy juz istnieja w bazie, na razie wpada tam wszystko
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/register")
    ResponseEntity<User> register(@RequestParam String name,
                                  @RequestParam String surname,
                                  @RequestParam String phone,
                                  @RequestParam Long cityId,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam Long roleId) {

        userService.validateRegistration(name, surname, phone, email, password);
        User user = userService.addNewUser(name, surname, phone, email, cityId, password, roleId);

        return ResponseEntity.created(URI.create("/" + user.getId())).body(user);
    }
}
