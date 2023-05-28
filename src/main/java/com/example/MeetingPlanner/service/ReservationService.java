package com.example.MeetingPlanner.service;

import com.example.MeetingPlanner.Enum.MeetingType;
import com.example.MeetingPlanner.dto.ReservationDTO;
import com.example.MeetingPlanner.model.MeetingRoom;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAllReservations();

    ReservationDTO createReservation(ReservationDTO reservationRequest);

    ReservationDTO getReservationById(Long id);

    ReservationDTO updateReservation(Long id, ReservationDTO reservationRequest);

    void deleteReservation(Long id);

    void validateReservationEquipment(MeetingType meetingType, MeetingRoom meetingRoom);
}
