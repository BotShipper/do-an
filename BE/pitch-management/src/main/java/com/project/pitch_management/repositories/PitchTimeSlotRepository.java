package com.project.pitch_management.repositories;

import com.project.pitch_management.entities.PitchTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitchTimeSlotRepository extends JpaRepository<PitchTimeSlot, Long> {
}
