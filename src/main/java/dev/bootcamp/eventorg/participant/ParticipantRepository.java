package dev.bootcamp.eventorg.participant;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

}
