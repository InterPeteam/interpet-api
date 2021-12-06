package com.interteam.interpet.api.controller.user;

import com.interteam.interpet.api.model.User;

import java.util.List;

public class UserMapper {

    //TODO dodać zwracanie listy rates
    public UserDto map(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .city(user.getCity().getName())
                .phone(user.getPhone())
                .role(user.getRole().getName())
                .rates(List.of())
                .build();
    }
}
