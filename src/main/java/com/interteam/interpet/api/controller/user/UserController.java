package com.interteam.interpet.api.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getUser() {
        System.out.println("Oh hi mark");
        return ResponseEntity.badRequest()
                .build();
    }

    @PatchMapping("/{userId}")
    ResponseEntity<Void> addOffer(@RequestBody UserDto userDto) {
        return ResponseEntity.badRequest()
                .build();
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser() {
        return ResponseEntity.badRequest()
                .build();
    }
}
