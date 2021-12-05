package com.interteam.interpet.api.controller.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserDto {
    //todo like offerDto
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private String id;
    private String email;
    private String name;
    private String surname;
    private String city;
    private String phone;
    private String description;
    private String roleName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Rate[] rates;

    @Getter
    static class Rate {
        private int rate;
        private String review;
    }
}
