package com.interteam.interpet.api.controller.offer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO: implement mappers & potential validators in endpoints

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
class OfferController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addOffer(@RequestBody OfferDto offerDto) {
        boolean validOfferRequest = true;

        if (validOfferRequest) {
            return ResponseEntity.noContent()
                    .build();
        }

        return ResponseEntity.badRequest()
                .build();
    }

    @GetMapping
    ResponseEntity<OfferDto[]> getOffers() {
        return ResponseEntity.badRequest()
                .build();
    }

    @GetMapping("/{offerId}")
    ResponseEntity<OfferDto> getOffer() {
        return ResponseEntity.badRequest()
                .build();
    }

    @DeleteMapping("/{offerId}")
    ResponseEntity<Void> deleteOffer() {
        return ResponseEntity.badRequest()
                .build();
    }

    @GetMapping("/{offerId}/applications")
    ResponseEntity<Void> getApplications(@RequestBody OfferDto.ApplicationDto applicationDto) {
        return ResponseEntity.badRequest()
                .build();
    }

    @PostMapping("/{offerId}/applications")
    ResponseEntity<OfferDto.ApplicationDto> addApplication(@RequestBody OfferDto.ApplicationDto applicationDto) {
        return ResponseEntity.badRequest()
                .build();
    }

    @PostMapping("/{offerId}/applications/{applicationId}/accept")
    ResponseEntity<Void> acceptApplication(@RequestBody boolean accepted) {
        return ResponseEntity.badRequest()
                .build();
    }

}
