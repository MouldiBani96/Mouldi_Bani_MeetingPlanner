package com.example.MeetingPlanner.controller;

import com.example.MeetingPlanner.dto.MeetingRoomDTO;
import com.example.MeetingPlanner.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meeting-rooms")
public class MeetingRoomController {
    private final MeetingRoomService meetingRoomService;

    @Autowired
    public MeetingRoomController(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping
    public ResponseEntity<List<MeetingRoomDTO>> getAllMeetingRooms() {
        List<MeetingRoomDTO> meetingRooms = meetingRoomService.getAllMeetingRooms();
        return new ResponseEntity<>(meetingRooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingRoomDTO> getMeetingRoomById(@PathVariable Long id) {
        MeetingRoomDTO meetingRoom = meetingRoomService.getMeetingRoomById(id);
        return new ResponseEntity<>(meetingRoom, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MeetingRoomDTO> createMeetingRoom( @RequestBody MeetingRoomDTO meetingRoomDTO) {
        MeetingRoomDTO createdMeetingRoom = meetingRoomService.createMeetingRoom(meetingRoomDTO);
        return new ResponseEntity<>(createdMeetingRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeetingRoomDTO> updateMeetingRoom(
            @PathVariable Long id,
             @RequestBody MeetingRoomDTO meetingRoomDTO
    ) {
        MeetingRoomDTO updatedMeetingRoom = meetingRoomService.updateMeetingRoom(id, meetingRoomDTO);
        return new ResponseEntity<>(updatedMeetingRoom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeetingRoom(@PathVariable Long id) {
        meetingRoomService.deleteMeetingRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
