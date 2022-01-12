package com.interteam.interpet.api.controller.rate;

import com.interteam.interpet.api.model.Rate;

public class RateMapper {

    public RateDto map(Rate rate) {
        return RateDto.builder()
                .id(rate.getId())
                .publisherId(rate.getPublisherId())
                .offerId(rate.getOfferId())
                .userId(rate.getUser().getId())
                .rate(rate.getRate())
                .description(rate.getDescription())
                .build();
    }
}
