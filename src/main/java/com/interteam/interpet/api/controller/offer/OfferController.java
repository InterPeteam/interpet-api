package com.interteam.interpet.api.controller.offer;


import com.interteam.interpet.api.repository.ApplicationRepository;
import com.interteam.interpet.api.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// TODO: implement mappers & potential validators in endpoints

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
class OfferController {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

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
    ResponseEntity<List<Application>> getApplications(@PathVariable("offerId") Integer offerId) {
        List<Application> applicationResults = applicationRepository.findByOfferId(offerId);
        return applicationResults.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(applicationResults);
    }

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

    @PostMapping("applications/{applicationId}/accept")
    ResponseEntity<Void> acceptApplication(@RequestBody boolean accepted,
                                           @PathVariable("applicationId") Integer applicationId) {
        Optional<Application> applicationResult = applicationRepository.findById(applicationId);
        if (applicationResult.isPresent()) {
            applicationResult.get().setAccepted(accepted);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

}
