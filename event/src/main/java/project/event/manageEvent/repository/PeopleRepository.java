package project.event.manageEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.event.manageEvent.entity.People;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<People, Long> {

    Optional<People> findByPeopleTCNo(String peopleTCNo);


}
