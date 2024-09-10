package dev.bootcamp.eventorg.controller;

import dev.bootcamp.eventorg.model.Participant;
import dev.bootcamp.eventorg.service.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("")
    public List<Participant> findAll() {
        return participantService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Participant create(@Valid @RequestBody Participant participant) {
        return participantService.create(participant);
    }
}
