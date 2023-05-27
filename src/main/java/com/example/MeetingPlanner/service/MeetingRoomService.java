package com.example.MeetingPlanner.service;

import com.example.MeetingPlanner.dto.MeetingRoomDTO;
import com.example.MeetingPlanner.model.MeetingRoom;
import java.util.List;

public interface MeetingRoomService {
    List<MeetingRoomDTO> getAllMeetingRooms();
    MeetingRoomDTO getMeetingRoomById(Long id);
    MeetingRoomDTO createMeetingRoom(MeetingRoomDTO meetingRoomDTO);
    MeetingRoomDTO updateMeetingRoom(Long id, MeetingRoomDTO meetingRoomDTO);
    void deleteMeetingRoom(Long id);
    // Ajoutez d'autres méthodes spécifiques aux salles de réunion
}
