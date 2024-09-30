package com.project.pitch_management.repositories;

import com.project.pitch_management.entities.PitchTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitchTimeSlotRepository extends JpaRepository<PitchTimeSlot, Long> {

    @Query("SELECT pts FROM PitchTimeSlot pts WHERE pts.pitch.id = :pitchId")
    List<PitchTimeSlot> findAllPitchTimeSlotsByPitch(@Param("pitchId") Long pitchId);
}
