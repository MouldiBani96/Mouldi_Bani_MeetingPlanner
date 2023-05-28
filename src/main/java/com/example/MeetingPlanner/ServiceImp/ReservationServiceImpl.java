package com.example.MeetingPlanner.ServiceImp;

import com.example.MeetingPlanner.Enum.MeetingType;
import com.example.MeetingPlanner.dto.ReservationDTO;
import com.example.MeetingPlanner.Exception.InvalidReservationException;
import com.example.MeetingPlanner.model.MeetingRoom;
import com.example.MeetingPlanner.model.Reservation;
import com.example.MeetingPlanner.repository.ReservationRepository;
import com.example.MeetingPlanner.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private  ModelMapper modelMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new InvalidReservationException("Invalid reservation ID: " + id));
        return convertToDTO(reservation);
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = convertToEntity(reservationDTO);
        //get exepetion if  meeting room does'nt have equipment necessary
        validateReservationEquipment(reservation.getMeetingRoom().getMeetingType(), reservation.getMeetingRoom());
        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToDTO(savedReservation);
    }

    @Override
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        // Retrieve the existing reservation
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new InvalidReservationException("Invalid reservation ID: " + id));
        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return convertToDTO(updatedReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    private Reservation convertToEntity(ReservationDTO reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }
    //get exepetion if  meeting room does'nt have equipment necessary

    public  void validateReservationEquipment(MeetingType meetingType, MeetingRoom meetingRoom) {
        if (meetingType == MeetingType.VC && (!meetingRoom.isHasWebcam() || !meetingRoom.isHasOctopus() || !meetingRoom.isHasScreen() )) {
            throw new IllegalArgumentException("The selected meeting room does not have the required equipment for a VC meeting.");
        } else if (meetingType == MeetingType.SPEC && !meetingRoom.isHasTable()) {
            throw new IllegalArgumentException("The selected meeting room does not have the required equipment for a SPEC meeting.");
        } else if (meetingType == MeetingType.RC &&(!meetingRoom.isHasTable() || !meetingRoom.isHasOctopus() || !meetingRoom.isHasScreen() )) {
            throw new IllegalArgumentException("The selected meeting room does not have the required equipment for an RC meeting.");
        }
    }

}
