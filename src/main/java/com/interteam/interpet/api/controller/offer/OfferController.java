package com.interteam.interpet.api.controller.offer;


import com.interteam.interpet.api.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

// TODO: implement mappers & potential validators in endpoints

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
class OfferController {
    @Autowired
    private OfferRepository offerRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addOffer(@RequestBody Offer offer) {
        boolean validOfferRequest = true;
        if (!validOfferRequest) {
            return ResponseEntity.badRequest().build();
        }
        offerRepository.save(offer);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<Iterable<Offer>> getOffers() {
        return ResponseEntity.ok(offerRepository.findAll());
    }

    @GetMapping("/{offerId}")
    ResponseEntity<Offer> getOffer(@PathVariable("offerId") Integer offerId) {
        Optional<Offer> offer = offerRepository.findById(offerId);
        return offer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{offerId}")
    ResponseEntity<Void> deleteOffer(@PathVariable("offerId") Integer offerId) {
        try{
            offerRepository.deleteById(offerId);
        }catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{offerId}/applications")
    ResponseEntity<Void> getApplications(@RequestBody Application application) {
        return ResponseEntity.badRequest()
                .build();
    }

    @PostMapping("/{offerId}/applications")
    ResponseEntity<Application> addApplication(@RequestBody Application application) {
        return ResponseEntity.badRequest()
                .build();
    }

    @PostMapping("/{offerId}/applications/{applicationId}/accept")
    ResponseEntity<Void> acceptApplication(@RequestBody boolean accepted) {
        return ResponseEntity.badRequest()
                .build();
    }

}
