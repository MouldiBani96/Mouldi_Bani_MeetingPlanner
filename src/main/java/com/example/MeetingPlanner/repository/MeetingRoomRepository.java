package com.example.MeetingPlanner.repository;

import com.example.MeetingPlanner.model.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
    // Ajoutez des méthodes supplémentaires si nécessaire
}
