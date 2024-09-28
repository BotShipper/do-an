package com.project.pitch_management.repositories;

import com.project.pitch_management.entities.BookPitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPitchRepository extends JpaRepository<BookPitch, Long> {
}
