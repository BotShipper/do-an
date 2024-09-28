package com.project.pitch_management.controllers.publics;

import com.project.pitch_management.dto.requests.UserRequest;
import com.project.pitch_management.dto.responses.ApiResponse;
import com.project.pitch_management.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/public")
public class UserControllerPublic {

    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request) {
        try {
            ApiResponse apiResponse = ApiResponse.builder()
                    .status(200)
                    .result(userService.register(request))
                    .message("Registered successfully")
                    .build();
            return ResponseEntity.ok().body(apiResponse);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .status(400)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .result(userService.login(request))
                .message("Login successfully")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
