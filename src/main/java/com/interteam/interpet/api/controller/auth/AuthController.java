package com.interteam.interpet.api.controller.auth;

import com.interteam.interpet.api.controller.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO: implement mappers & potential validators in endpoints

@RestController
@RequiredArgsConstructor
class AuthController {
    @PostMapping("/login")
    ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.badRequest()
                .build();
    }

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody UserDto userDto) {
        return ResponseEntity.badRequest()
                .build();
    }
}
