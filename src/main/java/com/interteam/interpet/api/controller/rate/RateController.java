package com.interteam.interpet.api.controller.rate;

import com.interteam.interpet.api.model.Offer;
import com.interteam.interpet.api.model.Rate;
import com.interteam.interpet.api.model.User;
import com.interteam.interpet.api.repository.OfferRepository;
import com.interteam.interpet.api.repository.RateRepository;
import com.interteam.interpet.api.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rates")
public class RateController {

    private final UserRepository userRepository;
    private final RateRepository rateRepository;
    private final OfferRepository offerRepository;
    private final RateMapper mapper = new RateMapper();

    public RateController(UserRepository userRepository,
                          RateRepository rateRepository,
                          OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
        this.offerRepository = offerRepository;
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @GetMapping("/user/{userId}")
    ResponseEntity<List<RateDto>> getRateByUser(@PathVariable("userId") Long userId) {

        Optional<User> user = userRepository.findById(userId);
        Set<Rate> rates;

        List<RateDto> rateDtos = new ArrayList<>();

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
    @GetMapping("/offer/{offerId}")
    ResponseEntity<List<RateDto>> getRateByOffer(@PathVariable("offerId") Long offerId) {

        Optional<Offer> offer = offerRepository.findById(Math.toIntExact(offerId));
        List<Rate> rates;

        List<RateDto> rateDtos = new ArrayList<>();

        if (offer.isPresent()) {
            rates = rateRepository.findRatesByOfferId(offerId);

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
                                       @RequestParam Long offerId,
                                       @RequestParam int rate,
                                       @RequestParam String description) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Offer> offer = offerRepository.findById(Math.toIntExact(offerId));

        if (user.isPresent() && offer.isPresent()) {
            Rate userRate = Rate.builder()
                    .user(user.get())
                    .publisherId(publisherId)
                    .offerId(offerId)
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
