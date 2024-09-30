package com.project.pitch_management.controllers.publics;

import com.project.pitch_management.dto.responses.ApiResponse;
import com.project.pitch_management.services.PitchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("public/pitches")
public class PitchControllerPublic {

    PitchService pitchService;

    @GetMapping
    public ResponseEntity<ApiResponse> getPitchesAll() {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .message("Created pitch success")
                .result(pitchService.getPitchesAll())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }
    
    @GetMapping("/{pitchId}/available-timeslots")
    public ResponseEntity<ApiResponse> getAvailableTimeSlots(@PathVariable Long pitchId, @RequestParam LocalDate dateBook) {

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .message("Get pitch success")
                .result(pitchService.getAvailableTimeSlots(pitchId, dateBook))
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }
}
