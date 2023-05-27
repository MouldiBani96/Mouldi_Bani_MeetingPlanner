package com.example.MeetingPlanner.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    private boolean hasScreen;
    private boolean hasWebcam;
    private boolean hasOctopus;
    private boolean hasTable;

    // Getters, setters, and constructors
}