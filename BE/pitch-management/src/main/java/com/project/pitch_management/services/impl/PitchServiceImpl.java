package com.project.pitch_management.services.impl;

import com.project.pitch_management.dto.requests.PitchRequest;
import com.project.pitch_management.dto.responses.PitchResponse;
import com.project.pitch_management.dto.responses.PitchTimeSlotResponse;
import com.project.pitch_management.entities.Pitch;
import com.project.pitch_management.entities.PitchTimeSlot;
import com.project.pitch_management.mappers.PitchMapper;
import com.project.pitch_management.repositories.BookPitchRepository;
import com.project.pitch_management.repositories.PitchRepository;
import com.project.pitch_management.repositories.PitchTimeSlotRepository;
import com.project.pitch_management.repositories.TimeSlotRepository;
import com.project.pitch_management.services.PitchService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class PitchServiceImpl implements PitchService {

    PitchMapper pitchMapper;
    PitchRepository pitchRepository;
    TimeSlotRepository timeSlotRepository;
    BookPitchRepository bookPitchRepository;
    PitchTimeSlotRepository pitchTimeSlotRepository;

    @Override
    public PitchResponse createPitch(PitchRequest request) {

        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        // Lưu vào cơ sở dữ liệu
        Pitch pitch = pitchMapper.toPitch(request);
        pitch.setCreateAt(localDateTime);
        pitch.setUpdateAt(localDateTime);
        pitchRepository.save(pitch);

        log.info("Created Pitch Success: {}", request);

        return pitchMapper.toPitchResponse(pitch);
    }

    @Override
    public List<PitchResponse> getPitchesAll() {
        return pitchRepository.findAll().stream().map(pitchMapper::toPitchResponse).toList();
    }

    @Override
    public PitchResponse getPitchById(long id) {

        Pitch pitch = pitchRepository.findById(id).orElseThrow();

        return pitchMapper.toPitchResponse(pitch);
    }

    @Override
    public PitchResponse getAvailableTimeSlots(long id, LocalDate dateBook) {

        List<PitchTimeSlot> allTimeSlots = pitchTimeSlotRepository.findAllPitchTimeSlotsByPitch(id);
        List<Long> bookedTimeSlotIds = bookPitchRepository.findBookedTimeSlotIds(id, dateBook);
        PitchResponse response = getPitchById(id);

        List<PitchTimeSlotResponse> result = new ArrayList<>();
        for (PitchTimeSlot pitchTimeSlot : allTimeSlots) {
            String status = bookedTimeSlotIds.contains(pitchTimeSlot.getTimeSlot().getId()) ? "Booked" : "Available";
            result.add(PitchTimeSlotResponse.builder()
                    .price(pitchTimeSlot.getPrice())
                    .startTime(pitchTimeSlot.getTimeSlot().getStartTime())
                    .endTime(pitchTimeSlot.getTimeSlot().getEndTime())
                    .status(status)
                    .build());
        }

        response.setPitchTimeSlots(result);

        return response;
    }
}
