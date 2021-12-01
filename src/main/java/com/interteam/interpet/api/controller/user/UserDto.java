package com.interteam.interpet.api.controller.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private String city;
    private String phone;
    private String description;
    private String roleName;
    private Rate[] rates;

    @Getter
    static class Rate {
        private int rate;
        private String review;
    }
}
