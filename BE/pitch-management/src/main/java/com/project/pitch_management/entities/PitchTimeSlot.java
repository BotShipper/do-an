package com.project.pitch_management.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "pitch_time_slot")
public class PitchTimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "closed")
    boolean isClosed;

    Double price;

    @ManyToOne
    @JoinColumn(name = "pitch_id")
    Pitch pitch;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    TimeSlot timeSlot;
}
