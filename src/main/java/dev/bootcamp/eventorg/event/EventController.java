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
        eventRepository.create(event);
    }

    // PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Event event,
                @PathVariable Integer id) {
        eventRepository.update(event,id);
    }

    // DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        eventRepository.delete(id);
    }

}
