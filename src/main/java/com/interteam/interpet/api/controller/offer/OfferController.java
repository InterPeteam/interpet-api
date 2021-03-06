package com.interteam.interpet.api.controller.offer;


import com.interteam.interpet.api.model.Animal;
import com.interteam.interpet.api.model.Application;
import com.interteam.interpet.api.model.Offer;
import com.interteam.interpet.api.model.User;
import com.interteam.interpet.api.repository.AnimalRepository;
import com.interteam.interpet.api.repository.ApplicationRepository;
import com.interteam.interpet.api.repository.OfferRepository;
import com.interteam.interpet.api.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: implement mappers & potential validators in endpoints

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
class OfferController {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addOffer(@RequestBody Offer offer) {
        boolean validOfferRequest = true;
        if (!validOfferRequest) {
            return ResponseEntity.badRequest().build();
        }
        offerRepository.save(offer);
        for(Animal animal: offer.getAnimals()) {
            animal.setOffer(offer);
            animalRepository.save(animal);
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @GetMapping
    ResponseEntity<Iterable<Offer>> getOffers(Authentication authentication) {
        User user = userRepository.getUserByEmail((String) authentication.getPrincipal());
        return ResponseEntity.ok(
            offerRepository.findByEndDateGreaterThan(new Date()).stream()
                .filter(o -> o.getUserId() != user.getId()).collect(Collectors.toList())
        );
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @GetMapping("/{offerId}")
    ResponseEntity<Offer> getOffer(@PathVariable("offerId") Integer offerId) {
        Optional<Offer> offer = offerRepository.findById(offerId);
        return offer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/{offerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> editOffer(@PathVariable("offerId") Integer offerId, @RequestBody Offer offer) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        if (offerDB.isPresent()) {
            offer.setId(offerId);
            offerRepository.save(offer);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/{offerId}/animal/{animalId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> editOffer(@PathVariable("offerId") Integer offerId, @PathVariable("animalId") Integer animalId,
                                   @RequestBody Animal animal) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        Optional<Animal> animalDB = animalRepository.findById(animalId);
        if (offerDB.isPresent() && animalDB.isPresent()) {
            animal.setId(animalId);
            animal.setOffer(offerDB.get());
            animalRepository.save(animal);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @GetMapping("/user/{userId}")
    ResponseEntity<Iterable<Offer>> getOfferByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(offerRepository.findByUserId(userId));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{offerId}")
    ResponseEntity<Void> deleteOffer(@PathVariable("offerId") Integer offerId) {
        Optional<Offer> offer = offerRepository.findById(offerId);
        if (offer.isPresent()) {
            for(Animal animal: offer.get().getAnimals()) {
                animalRepository.deleteById(animal.getId());
            }
            for(Application app: offer.get().getApplications()){
                applicationRepository.deleteById(app.getId());
            }
            offerRepository.deleteById(offerId);
        } else return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @GetMapping("/{offerId}/applications")
    ResponseEntity<List<Application>> getApplications(@PathVariable("offerId") Integer offerId) {
        return ResponseEntity.ok(applicationRepository.findByOfferId(offerId));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PostMapping("/{offerId}/applications")
    ResponseEntity<Application> addApplication(@RequestBody Application application,
                                               @PathVariable("offerId") Integer offerId) {
        boolean isApplicationValid = true;
        if (!isApplicationValid) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Offer> offer = offerRepository.findById(offerId);
        if (offer.isPresent()) {
            application.setOffer(offer.get());
            applicationRepository.save(application);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PostMapping("applications/{applicationId}/accept")
    ResponseEntity<Void> acceptApplication(@RequestBody boolean accepted,
                                           @PathVariable("applicationId") Integer applicationId) {
        Optional<Application> applicationResult = applicationRepository.findById(applicationId);
        if (applicationResult.isPresent()) {
            applicationResult.get().setAccepted(accepted);
            applicationRepository.save(applicationResult.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

}
