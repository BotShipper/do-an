package com.project.pitch_management.repositories;

import com.project.pitch_management.entities.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, Long> {

    Optional<Pitch> findByIdAndBookPitchesDateBook(Long pitchId, LocalDate dateBook);

}
