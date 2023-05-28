package com.example.MeetingPlanner.dto;

import com.example.MeetingPlanner.Enum.MeetingType;
import lombok.Data;

import java.util.List;

@Data
public class MeetingRoomDTO {
    private Long id;
    private String name;
    private int capacity;
    private MeetingType meetingType;
    private List<ReservationDTO> reservations;
    private boolean hasScreen;
    private boolean hasWebcam;
    private boolean hasOctopus;
    private boolean hasTable;
}
