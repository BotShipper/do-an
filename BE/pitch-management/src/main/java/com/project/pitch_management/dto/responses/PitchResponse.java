package com.project.pitch_management.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PitchResponse {

    Long id;
    String name;
    String address;
    String image;
    String type;
    LocalDateTime createAt;
    LocalDateTime updateAt;

    List<BookPitchResponse> bookPitches;
    List<PitchTimeSlotResponse> pitchTimeSlots;
}
