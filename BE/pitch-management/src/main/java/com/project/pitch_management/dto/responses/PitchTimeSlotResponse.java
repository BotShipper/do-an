package com.project.pitch_management.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PitchTimeSlotResponse {

    Double price;

    // TimeSlot
    LocalTime startTime;
    LocalTime endTime;

    // Thêm trường
    String status;
}
