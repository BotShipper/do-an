package com.project.pitch_management.services.impl;

import com.project.pitch_management.dto.requests.PitchRequest;
import com.project.pitch_management.dto.responses.PitchResponse;
import com.project.pitch_management.entities.BookPitch;
import com.project.pitch_management.entities.Pitch;
import com.project.pitch_management.mappers.PitchMapper;
import com.project.pitch_management.repositories.BookPitchRepository;
import com.project.pitch_management.repositories.PitchRepository;
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
    public PitchResponse getPitchByIdAndDate(long id, LocalDate date) {

        Pitch pitch = pitchRepository.findById(id).orElseThrow();

        // Lọc các bookPitches theo dateBook
        List<BookPitch> filteredBookPitches = pitch.getBookPitches().stream()
                .filter(bp -> bp.getDateBook().equals(date))
                .toList();

        pitch.setBookPitches(filteredBookPitches);

//        // Duyệt qua các PitchTimeSlots và gán TimeSlot cho mỗi PitchTimeSlot
//        List<PitchTimeSlot> updatedPitchTimeSlots = pitch.getPitchTimeSlots().stream()
//                .map(pitchTimeSlot -> {
//                    TimeSlot timeSlot = timeSlotRepository.findById(pitchTimeSlot.getTimeSlot().getId())
//                            .orElseThrow();
//                    pitchTimeSlot.setTimeSlot(timeSlot);  // Gán TimeSlot cho PitchTimeSlot
//                    return pitchTimeSlot;  // Trả về PitchTimeSlot đã cập nhật
//                })
//                .toList();  // Thu thập kết quả thành danh sách mới
//
//        // Gán danh sách PitchTimeSlots đã cập nhật lại vào Pitch
//        pitch.setPitchTimeSlots(updatedPitchTimeSlots);

        PitchResponse response = pitchMapper.toPitchResponse(pitch);

        return null;
    }
}
