package com.interteam.interpet.api.controller.offer;

import com.interteam.interpet.api.controller.user.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OfferDto {
    private String animal;
    private float price;
    private String startDate;
    private String endDate;
    private String description;

    public static class ApplicationDto {
        private UserDto caretaker;
        private boolean accepted;
    }
}
