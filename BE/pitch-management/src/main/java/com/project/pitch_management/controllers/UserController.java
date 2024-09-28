package com.project.pitch_management.controllers;

import com.project.pitch_management.dto.responses.ApiResponse;
import com.project.pitch_management.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "userId") long id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .result(userService.getUserById(id))
                .message("Registered successfully")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
