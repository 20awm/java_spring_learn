package dev.bootcamp.eventorg.participant;

import dev.bootcamp.eventorg.event.Event;
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
