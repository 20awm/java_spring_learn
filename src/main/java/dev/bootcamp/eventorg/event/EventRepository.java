package dev.bootcamp.eventorg.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    private final List<Event> events = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(
            EventRepository.class);

    private final JdbcClient jdbcClient;

    public EventRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Event> findAll() {
        return jdbcClient.sql("select * from event")
                .query(Event.class)
                .list();
    }

    public Optional<Event> findById(Integer id) {
        return jdbcClient.sql("select id,title,start_on,complete_on,participant,location " +
                "from Event where id = :id")
                .param("id",id)
                .query(Event.class)
                .optional();
    }

    public void create(Event event) {
        var updated = jdbcClient.sql("insert into Event(id,title,start_on,complete_on,participant,location) " +
                "values (?,?,?,?,?,?)")
                .params(List.of(
                        event.id(),
                        event.title(),
                        event.startOn(),
                        event.completeOn(),
                        event.participant(),
                        event.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create event " + event.title());
    }

    public void update(Event event, Integer id) {
        var sqlUpdate = "UPDATE Event SET title = ?,start_on = ?,complete_on = ?,participant = ?,location = ? WHERE id = ?";
        var updated = jdbcClient.sql(sqlUpdate)
                .params(List.of(
                        event.title(),
                        event.startOn(),
                        event.completeOn(),
                        event.participant(),
                        event.location().toString(),
                        id
                ))
                .update();

        Assert.state(updated == 1,
                "Failed to update event " + event.title());
    }
    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM event where id = :id")
                .param("id",id)
                .update();
        Assert.state(updated == 1,
                "Failed to delete event " + id);
    }

}
//    List<Event> events = new ArrayList<>();
//
//    List<Event> findAll() {
//        return events;
//    }
//
//    // FIND BY ID (METHOD 1)
////    public Event findById(Integer id) {
////        return events.stream()
////                .filter(event -> event.getId()
////                .equals(id))
////                .findFirst()
////                .orElse(null);
////    }
//
//    // FIND BY ID (METHOD 2)
//    Optional<Event> findById(Integer id) {
//        return events.stream()
//                .filter(event -> Objects.equals(event.id(), id))
//                .findFirst();
//    }
//
//    // INSERT/CREATE NEW DATA
//    void create(Event event) {
//        events.add(event);
//    }
//
//    // UPDATE/MODIFY EXISTING DATA
//    void update(Event event, Integer id) {
//        Optional<Event> existingEvent = findById(id);
//        existingEvent.ifPresent(value -> events
//                .set(events.indexOf(value), event));
//    }
//
//    // DELETE/REMOVE EXISTING DATA
//    void delete(Integer id) {
//        events.removeIf(event -> event.id().equals(id));
//    }
//
//    @PostConstruct
//    private void init() {
//        events.add(new Event(1,
//                "Konser A",
//                LocalDateTime.now(),
//                LocalDateTime.now().plusHours(5),
//                1000,
//                Location.BEKASI));
//        events.add(new Event(2,
//                "Konser B",
//                LocalDateTime.now(),
//                LocalDateTime.now().plusHours(6),
//                1500,
//                Location.JAKARTA));
//    }
//}