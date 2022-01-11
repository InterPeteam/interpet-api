package com.interteam.interpet.api.controller.user;

import com.interteam.interpet.api.model.Rate;
import com.interteam.interpet.api.model.User;
import com.interteam.interpet.api.repository.UserRepository;
import com.interteam.interpet.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/users")

class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @GetMapping("/{userId}")
    ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {

        Optional<User> user = userRepository.findById(userId);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PutMapping("/{userId}")
    ResponseEntity<User> editUserMainSettings(@PathVariable("userId") Long userId,
                                              @RequestParam String name,
                                              @RequestParam String surname,
                                              @RequestParam String phone) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().editUserMainSettings(name, surname, phone);
            User modifyUser = userRepository.save(user.get());
            return ResponseEntity.ok(modifyUser);
        } else
            return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PutMapping("/{userId}/email")
    ResponseEntity<User> editUserMail(@PathVariable("userId") Long userId,
                                      @RequestParam String email) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().editUserMail(email);
            User modifyUser = userRepository.save(user.get());
            return ResponseEntity.ok(modifyUser);
        } else
            return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PutMapping("/{userId}/password")
    ResponseEntity<User> editUserPassword(@PathVariable("userId") Long userId,
                                          @RequestParam String password) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().editUserPassword(password);
            User modifyUser = userRepository.save(user.get());
            return ResponseEntity.ok(modifyUser);
        } else
            return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{userId}")
    ResponseEntity<Long> deleteUser(@PathVariable Long userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok(userId);
        } else
            return ResponseEntity.notFound().build();
    }
}
