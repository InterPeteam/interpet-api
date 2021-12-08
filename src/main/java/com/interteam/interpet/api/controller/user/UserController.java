package com.interteam.interpet.api.controller.user;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @GetMapping("/{userId}")
    ResponseEntity<String> getUser() {
        System.out.println("Oh hi mark");
        return ResponseEntity.ok("Apyr");
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @PatchMapping("/{userId}")
    ResponseEntity<Void> editUser(@RequestBody Void userDto) {
        return ResponseEntity.badRequest()
                .build();
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "authkey")})
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser() {
        return ResponseEntity.badRequest()
                .build();
    }
}
