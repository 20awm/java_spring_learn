package dev.bootcamp.eventorg.repository;

import dev.bootcamp.eventorg.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByLocation(String location);

    List<Event> findAllByLocationAndParticipant(String location, Integer participant);

    List<Event> findAllByParticipant(Integer participant);
}
