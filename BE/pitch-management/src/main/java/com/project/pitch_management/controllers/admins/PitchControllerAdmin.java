package com.project.pitch_management.controllers.admins;

import com.project.pitch_management.dto.requests.PitchRequest;
import com.project.pitch_management.dto.responses.ApiResponse;
import com.project.pitch_management.services.PitchService;
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
@RequestMapping("admin/pitches")
public class PitchControllerAdmin {

    PitchService pitchService;

    @PostMapping
    public ResponseEntity<ApiResponse> createPitch(@RequestBody PitchRequest request) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .message("Created pitch success")
                .result(pitchService.createPitch(request))
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }
}
