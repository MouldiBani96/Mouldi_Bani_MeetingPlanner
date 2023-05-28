package com.example.MeetingPlanner.model;
import com.example.MeetingPlanner.Enum.MeetingType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meeting_rooms")
@Data
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;
    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    private boolean hasScreen;
    private boolean hasWebcam;
    private boolean hasOctopus;
    private boolean hasTable;

    // Getters, setters, and constructors
}