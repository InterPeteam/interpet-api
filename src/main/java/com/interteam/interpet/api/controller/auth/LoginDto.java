package com.interteam.interpet.api.controller.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginDto {
    private String username;
    private String password;
}
