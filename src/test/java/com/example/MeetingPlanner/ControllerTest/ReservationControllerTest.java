package com.example.MeetingPlanner.ControllerTest;

import com.example.MeetingPlanner.dto.ReservationDTO;
import com.example.MeetingPlanner.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.MeetingPlanner.controller.ReservationController;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllReservations_ReturnsListOfReservations() {
        // Arrange
        ReservationDTO reservation1 = new ReservationDTO();
        ReservationDTO reservation2 = new ReservationDTO();
        List<ReservationDTO> reservations = Arrays.asList(reservation1, reservation2);

        when(reservationService.getAllReservations()).thenReturn(reservations);

        // Act
        ResponseEntity<List<ReservationDTO>> response = reservationController.getAllReservations();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    void getReservationById_ExistingId_ReturnsReservation() {
        // Arrange
        long reservationId = 1L;
        ReservationDTO reservation = new ReservationDTO();

        when(reservationService.getReservationById(reservationId)).thenReturn(reservation);

        // Act
        ResponseEntity<ReservationDTO> response = reservationController.getReservationById(reservationId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservation, response.getBody());
        verify(reservationService, times(1)).getReservationById(reservationId);
    }


}
