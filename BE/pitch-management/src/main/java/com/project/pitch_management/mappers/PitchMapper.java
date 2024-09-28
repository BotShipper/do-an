package com.project.pitch_management.mappers;

import com.project.pitch_management.dto.requests.PitchRequest;
import com.project.pitch_management.dto.responses.PitchResponse;
import com.project.pitch_management.entities.Pitch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PitchMapper {

    Pitch toPitch(PitchRequest request);

    PitchResponse toPitchResponse(Pitch pitch);
}
