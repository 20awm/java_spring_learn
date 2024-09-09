package dev.bootcamp.eventorg.event;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // GET ALL DATA
    @GetMapping("")
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    // GET DATA BY ID (Method 1)
//    @GetMapping("/api/events/{id}")
//    public Event findById(@PathVariable Integer id) {
//        return eventRepository.findById(id);
//    }

    // GET DATA BY ID (Method 2)
    @GetMapping("/{id}")
    Event findById(@PathVariable Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()) {
            throw new EventNotFoundException();
        }
        return event.get();
    }

    // GET DATA BY LOCATION

    // POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Event event) {
        eventRepository.save(event);
    }

    // PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Event event,
                @PathVariable Integer id) {
        if(!eventRepository.existsById(id)) {
            throw new EventNotFoundException();
        }
        Event existingEvent = eventRepository
                .findById(id).orElseThrow(EventNotFoundException::new);
        Event updatedEvent = new Event(
                id,
                event.title(),
                event.startOn(),
                event.completeOn(),
                event.participant(),
                event.location(),
                existingEvent.version()
        );
        eventRepository.save(updatedEvent);
    }

    // DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        eventRepository.delete(eventRepository.findById(id).get());
    }

    // FIND WITH PARAMETER
//    @GetMapping("/location")
//    List<Event> findByLocation(@RequestParam String location) {
//        return eventRepository.findAllByLocation(location);
//    }

    @GetMapping("/location")
    List<Event> findByLocationAndParticipant(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer participant) {

        if (location != null && participant != null) {
            return eventRepository.findAllByLocationAndParticipant(location, participant);
        } else if (location != null) {
            return eventRepository.findAllByLocation(location);
        } else if (participant != null) {
            return eventRepository.findAllByParticipant(participant);
        } else {
            return eventRepository.findAll();
        }
    }

    // FIND WITH PATH
    @GetMapping("/location/{location}")
    List<Event> findByLocation(@PathVariable String location) {
        return eventRepository.findAllByLocation(location);
    }
}
