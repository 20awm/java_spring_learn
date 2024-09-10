package dev.bootcamp.eventorg.service;

import dev.bootcamp.eventorg.model.Participant;
import dev.bootcamp.eventorg.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    // GET ALL PARTICIPANT DATA
    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    // POST PARTICIPANT DATA
    public Participant create(Participant participant) {
        return participantRepository.save(participant);
    }
}
