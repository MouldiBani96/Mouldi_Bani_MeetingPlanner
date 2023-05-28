package com.example.MeetingPlanner.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long meetingRoomId;

    // Getters, setters, and constructors
}