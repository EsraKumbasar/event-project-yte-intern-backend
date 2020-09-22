package project.event.manageEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.event.manageEvent.entity.Event;

import javax.transaction.Transactional;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByEventNumber(String eventNumber);

    @Transactional
    void deleteByEventNumber(String eventNumber);
}
