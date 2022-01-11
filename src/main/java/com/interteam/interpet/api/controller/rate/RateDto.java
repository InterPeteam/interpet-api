package com.interteam.interpet.api.controller.rate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RateDto {
    private Long id;
    private Long userId;
    private Long publisherId;
    private int rate;
    private String description;
}
