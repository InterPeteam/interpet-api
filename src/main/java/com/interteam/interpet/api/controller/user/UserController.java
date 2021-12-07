package com.interteam.interpet.api.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    @GetMapping("/{userId}")
    ResponseEntity<String> getUser() {
        System.out.println("Oh hi mark");
        return ResponseEntity.ok("Apyr");
    }

    @PatchMapping("/{userId}")
    ResponseEntity<Void> editUser(@RequestBody Void userDto) {
        return ResponseEntity.badRequest()
                .build();
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser() {
        return ResponseEntity.badRequest()
                .build();
    }
}
