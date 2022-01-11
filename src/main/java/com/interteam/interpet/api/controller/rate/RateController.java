package com.interteam.interpet.api.controller.rate;

import com.interteam.interpet.api.model.Rate;
import com.interteam.interpet.api.model.User;
import com.interteam.interpet.api.repository.UserRepository;
import com.interteam.interpet.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rates")
public class RateController {

    private final UserRepository userRepository;

    public RateController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @GetMapping("/{userId}")
    ResponseEntity<Set<Rate>> getRate(@PathVariable("userId") Long userId) {

        Optional<User> user = userRepository.findById(userId);
        Set<Rate> rate;

        if (user.isPresent()) {
            rate = user.get().getRates();
            return ResponseEntity.ok(rate);
        } else
            return ResponseEntity.notFound().build();

    }


    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PostMapping("/{userId}")
    ResponseEntity<Rate> addNewRate(@PathVariable("userId") Long userId,
                                    @RequestParam Long publisherId,
                                    @RequestParam int rate,
                                    @RequestParam String description) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Rate userRate = Rate.builder()
                    .publisherId(publisherId)
                    .rate(rate)
                    .description(description)
                    .build();

            user.get().addNewRate(userRate);

            userRepository.save(user.get());

            return ResponseEntity.ok(userRate);

        } else
            return ResponseEntity.notFound().build();

    }
}
