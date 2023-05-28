package com.example.MeetingPlanner.ControllerTest;
import com.example.MeetingPlanner.controller.MeetingRoomController;
import com.example.MeetingPlanner.dto.MeetingRoomDTO;
import com.example.MeetingPlanner.service.MeetingRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MeetingRoomControllerTest {

    @Mock
    private MeetingRoomService meetingRoomService;

    @InjectMocks
    private MeetingRoomController meetingRoomController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMeetingRooms_ReturnsListOfMeetingRooms() {
        // Arrange
        MeetingRoomDTO meetingRoom1 = new MeetingRoomDTO();
        meetingRoom1.setId(1L);
        MeetingRoomDTO meetingRoom2 = new MeetingRoomDTO();
        meetingRoom2.setId(2L);
        List<MeetingRoomDTO> expectedMeetingRooms = Arrays.asList(meetingRoom1, meetingRoom2);

        when(meetingRoomService.getAllMeetingRooms()).thenReturn(expectedMeetingRooms);

        // Act
        ResponseEntity<List<MeetingRoomDTO>> response = meetingRoomController.getAllMeetingRooms();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMeetingRooms, response.getBody());
        verify(meetingRoomService, times(1)).getAllMeetingRooms();
    }

    @Test
    void getMeetingRoomById_ExistingId_ReturnsMeetingRoom() {
        // Arrange
        long meetingRoomId = 1L;
        MeetingRoomDTO expectedMeetingRoom = new MeetingRoomDTO();
        expectedMeetingRoom.setId(meetingRoomId);

        when(meetingRoomService.getMeetingRoomById(meetingRoomId)).thenReturn(expectedMeetingRoom);

        // Act
        ResponseEntity<MeetingRoomDTO> response = meetingRoomController.getMeetingRoomById(meetingRoomId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMeetingRoom, response.getBody());
        verify(meetingRoomService, times(1)).getMeetingRoomById(meetingRoomId);
    }


}
