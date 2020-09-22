package project.event.manageEvent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.event.manageEvent.entity.Event;
import project.event.manageEvent.entity.People;
import project.event.common.entity.Question;
import project.event.manageEvent.repository.EventRepository;
import project.event.manageEvent.repository.PeopleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ManageEventService {

    private final EventRepository eventRepository;
    private final PeopleRepository peopleRepository;

    public List<Event> listAllEvents() {
        return eventRepository.findAll();
    }

    public List<People> listAllPeople() {
        return peopleRepository.findAll();
    }

    public Event getPeopleByEventNumber(String eventNumber) {
        return eventRepository.findByEventNumber(eventNumber).orElseThrow(EntityNotFoundException::new);
    }

    public String getEventByParticipantsSize(){
        String event = "";
        Long id = 1L;

        for(int i = 0; i < eventRepository.count(); i++){
            event += eventRepository.getOne(id).getSize();
            id++;
        }
        return event;
    }

    public String getEventByParticipantsDay(){
        String event = "";
        Long id = 1L;

        for(int i = 0; i < eventRepository.count(); i++){
            event += eventRepository.getOne(id).getDayDuration();
            id++;
        }
        return event;
    }


    public Set<People> getEventsPeople(String eventNumber) {
        return eventRepository.findByEventNumber(eventNumber).map(Event::getEventParticipants)
                .orElseThrow(EntityNotFoundException::new);
    }

  /* public Set<Event> getPeopleEvents(String peopleTCNo){
        return peopleRepository.findByPeopleTCNo(peopleTCNo).map(People::getEvents)
                .orElseThrow(EntityNotFoundException::new);
    } */

    public Optional<People> getPeople(String peopleTCNo){
        return peopleRepository.findByPeopleTCNo(peopleTCNo);
    }



    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(String eventNumber) {
        eventRepository.deleteByEventNumber(eventNumber);
    }

     // Etkinliğe kişi ekleme
    public People addPeopleToEvent(String eventNumber, People people) {
        Optional<Event> eventOptional = eventRepository.findByEventNumber(eventNumber);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            Set<People> participants = event.getEventParticipants();

            if (event.quota()) {
                throw new IllegalStateException();
            }

            if(people.getEvents().contains(event)){
                throw new IllegalStateException();
            }
/*
            String barcode = participants.toString();
            BufferedImage qrCode = QRGenBarcodeGenerator.generateQRCodeImage(barcode); */

            participants.add(people);
            Event savedEvent = eventRepository.save(event);

            return savedEvent
                    .getEventParticipants()
                    .stream()
                    .collect(toList())
                    .get(0);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Question addQuestionToEvent(String eventNumber, Question question) {
        Optional<Event> eventOptional = eventRepository.findByEventNumber(eventNumber);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            Set<Question> questions = event.getQuestions();

            questions.add(question);
            Event savedEvent = eventRepository.save(event);
            return savedEvent
                    .getQuestions()
                    .stream()
                    .collect(toList())
                    .get(0);
        } else {
            throw new EntityNotFoundException();
        }
    }


}
