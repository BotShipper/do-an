package com.project.pitch_management.repositories;

import com.project.pitch_management.entities.BookPitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookPitchRepository extends JpaRepository<BookPitch, Long> {

    @Query("SELECT bp.timeSlot.id FROM BookPitch bp WHERE bp.pitch.id = :pitchId AND bp.dateBook = :dateBook AND bp.isUsed = true")
    List<Long> findBookedTimeSlotIds(@Param("pitchId") Long pitchId, @Param("dateBook") LocalDate dateBook);
}
