package dev.bootcamp.eventorg.repository;

import dev.bootcamp.eventorg.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

}
