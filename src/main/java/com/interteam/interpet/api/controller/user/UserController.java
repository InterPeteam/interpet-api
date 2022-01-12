package com.interteam.interpet.api.controller.user;

import com.interteam.interpet.api.model.Rate;
import com.interteam.interpet.api.model.User;
import com.interteam.interpet.api.repository.RateRepository;
import com.interteam.interpet.api.repository.UserRepository;
import com.interteam.interpet.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final RateRepository rateRepository;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserRepository userRepository,
                          UserService userService,
                          RateRepository rateRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.rateRepository = rateRepository;
        this.passwordEncoder = passwordEncoder;
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
    ResponseEntity<Object> editUserMail(@PathVariable("userId") Long userId,
                                        @RequestParam String email) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            if (userService.checkIfMailExists(email)) {
                return ResponseEntity.badRequest().body("Podany email juz istnieje w bazie");
            }
            user.get().editUserMail(email);
            User modifyUser = userRepository.save(user.get());
            return ResponseEntity.ok(modifyUser);

        } else
            return ResponseEntity.notFound().build();
    }


    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PutMapping("/{userId}/password")
    ResponseEntity<Object> editUserPassword(@PathVariable("userId") Long userId,
                                            @RequestParam String password) {

        Optional<User> user = userRepository.findById(userId);

        if (password.length() < 2) {
            return ResponseEntity.badRequest().body("Za krótkie hasło");
        }

        if (user.isPresent()) {
            user.get().editUserPassword(passwordEncoder.encode(password));
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

            List<Rate> findRates = rateRepository.findRatesByUserId(userId);
            rateRepository.deleteAll(findRates);
            userRepository.deleteById(userId);
            return ResponseEntity.ok(userId);
        } else
            return ResponseEntity.notFound().build();
    }
}
