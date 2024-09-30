package com.project.pitch_management.services;

import com.project.pitch_management.dto.requests.PitchRequest;
import com.project.pitch_management.dto.responses.PitchResponse;

import java.time.LocalDate;
import java.util.List;

public interface PitchService {

    PitchResponse createPitch(PitchRequest request);

    List<PitchResponse> getPitchesAll();

    PitchResponse getPitchById(long id);

    PitchResponse getAvailableTimeSlots(long id, LocalDate dateBook);

}
