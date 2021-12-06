package com.interteam.interpet.api.controller.user;

import com.interteam.interpet.api.model.Rate;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String phone;
    private String role;
    private List<Rate> rates;
}
