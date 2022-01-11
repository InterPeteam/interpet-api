package com.interteam.interpet.api.controller.rate;

import com.interteam.interpet.api.model.Rate;
import com.interteam.interpet.api.model.User;
import com.interteam.interpet.api.repository.RateRepository;
import com.interteam.interpet.api.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rates")
public class RateController {

    private final UserRepository userRepository;
    private final RateRepository rateRepository;
    private final RateMapper mapper = new RateMapper();

    public RateController(UserRepository userRepository,
                          RateRepository rateRepository) {
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @GetMapping("/{userId}")
    ResponseEntity<ArrayList<RateDto>> getRate(@PathVariable("userId") Long userId) {

        Optional<User> user = userRepository.findById(userId);
        Set<Rate> rates;

        ArrayList<RateDto> rateDtos = new ArrayList<>();

        if (user.isPresent()) {
            rates = user.get().getRates();

            for (Rate rate : rates) {
                rateDtos.add(mapper.map(rate));
            }
            return ResponseEntity.ok(rateDtos);
        } else
            return ResponseEntity.notFound().build();

    }


    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PostMapping("/{userId}")
    ResponseEntity<RateDto> addNewRate(@PathVariable("userId") Long userId,
                                       @RequestParam Long publisherId,
                                       @RequestParam int rate,
                                       @RequestParam String description) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Rate userRate = Rate.builder()
                    .user(user.get())
                    .publisherId(publisherId)
                    .rate(rate)
                    .description(description)
                    .build();

            user.get().addNewRate(userRate);
            Rate modifyRate = rateRepository.save(userRate);
            RateDto rateDto = mapper.map(modifyRate);
            userRepository.save(user.get());

            return ResponseEntity.ok(rateDto);
        } else
            return ResponseEntity.notFound().build();

    }
}
