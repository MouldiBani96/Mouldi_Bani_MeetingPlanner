package com.example.MeetingPlanner.ServiceImp;

import com.example.MeetingPlanner.Enum.MeetingType;
import com.example.MeetingPlanner.dto.MeetingRoomDTO;
import com.example.MeetingPlanner.model.MeetingRoom;
import com.example.MeetingPlanner.repository.MeetingRoomRepository;
import com.example.MeetingPlanner.service.MeetingRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {
    private final MeetingRoomRepository meetingRoomRepository;
    private  ModelMapper modelMapper;

    @Autowired
    public MeetingRoomServiceImpl(MeetingRoomRepository meetingRoomRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
    }

    @Override
    public List<MeetingRoomDTO> getAllMeetingRooms() {
        List<MeetingRoom> meetingRooms = meetingRoomRepository.findAll();
        return meetingRooms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MeetingRoomDTO getMeetingRoomById(Long id) {
        MeetingRoom meetingRoom = meetingRoomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Salle de réunion non trouvée avec l'ID: " + id));
        return convertToDTO(meetingRoom);
    }

    @Override
    public MeetingRoomDTO createMeetingRoom(MeetingRoomDTO meetingRoomDTO) {
        MeetingRoom meetingRoom = convertToEntity(meetingRoomDTO);
        // Set the boolean attributes based on the meeting type
        MeetingType meetingType = meetingRoomDTO.getMeetingType();
        switch (meetingType) {
            case VC:
                meetingRoom.setHasScreen(true);
                meetingRoom.setHasWebcam(true);
                meetingRoom.setHasOctopus(true);
                break;
            case SPEC:
                meetingRoom.setHasTable(true);
                break;
            case RS:
                // No specific equipment needed
                break;
            case RC:
                meetingRoom.setHasTable(true);
                meetingRoom.setHasScreen(true);
                meetingRoom.setHasOctopus(true);
                break;
        }
        MeetingRoom savedMeetingRoom = meetingRoomRepository.save(meetingRoom);
        return convertToDTO(savedMeetingRoom);
    }


    @Override
    public MeetingRoomDTO updateMeetingRoom(Long id, MeetingRoomDTO meetingRoomDTO) {
        MeetingRoom existingMeetingRoom = meetingRoomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Salle de réunion non trouvée avec l'ID: " + id));
        existingMeetingRoom.setName(meetingRoomDTO.getName());
        existingMeetingRoom.setCapacity(meetingRoomDTO.getCapacity());
        // Mettez à jour d'autres attributs selon les besoins

        MeetingRoom updatedMeetingRoom = meetingRoomRepository.save(existingMeetingRoom);
        return convertToDTO(updatedMeetingRoom);
    }

    @Override
    public void deleteMeetingRoom(Long id) {
        meetingRoomRepository.deleteById(id);
    }

    private MeetingRoomDTO convertToDTO(MeetingRoom meetingRoom) {
        return modelMapper.map(meetingRoom, MeetingRoomDTO.class);
    }

    private MeetingRoom convertToEntity(MeetingRoomDTO meetingRoomDTO) {
        return modelMapper.map(meetingRoomDTO, MeetingRoom.class);
    }
}
