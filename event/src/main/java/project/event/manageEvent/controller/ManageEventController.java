package project.event.manageEvent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.event.barcode.QRGenBarcodeGenerator;
import project.event.common.mapper.QuestionMapper;
import project.event.manageEvent.dto.EventDto;
import project.event.manageEvent.dto.PeopleDto;
import project.event.common.dto.QuestionDto;
import project.event.manageEvent.entity.Event;
import project.event.manageEvent.entity.People;
import project.event.common.entity.Question;
import project.event.manageEvent.excaption.ResourceNotFoundException;
import project.event.manageEvent.mapper.EventMapper;
import project.event.manageEvent.mapper.PeopleMapper;
import project.event.manageEvent.repository.EventRepository;
import project.event.manageEvent.service.ManageEventService;

import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/test")
public class ManageEventController {

    private final ManageEventService manageEventService;
    private final PeopleMapper peopleMapper;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final QuestionMapper questionMapper;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }


    @GetMapping("/list")
    public List<EventDto> listAllEvents() {
        List<Event> Event = manageEventService.listAllEvents();
        return eventMapper.mapToDto(Event);
    }

    @GetMapping("/list/people")
    public List<PeopleDto> listAllPeople() {
        List<People> people = manageEventService.listAllPeople();
        return peopleMapper.mapToDTo(people);
    }

    @GetMapping("/participantsSize")
    public String listEventByParticipantSize(){
        return  manageEventService.getEventByParticipantsSize();
    }

    @GetMapping("/participantsDay")
    public String listEventByParticipantDay(){
        return  manageEventService.getEventByParticipantsDay();
    }


    @PutMapping("/admin/{eventNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> updateEvent(@PathVariable String eventNumber, @Valid @RequestBody Event eventDetails) {
        Event event = eventRepository.findByEventNumber(eventNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Event not exist with event number :" + eventNumber));

        event.setEventName(eventDetails.getEventName());
        event.setStartDate(eventDetails.getStartDate());
        event.setEndDate(eventDetails.getEndDate());
        event.setQuota(eventDetails.getQuota());


        Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }


    @PostMapping("/admin/add")
    @PreAuthorize("hasRole('ADMIN')")
    public EventDto addEvent(@Valid @RequestBody EventDto eventDto) {
        Event event = eventMapper.mapToEntity(eventDto);
        Event addedEvent = manageEventService.addEvent(event);
        return eventMapper.mapToDto(addedEvent);
    }

    @DeleteMapping("/{eventNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEvent(@PathVariable String eventNumber) {
        manageEventService.deleteEvent(eventNumber);
    }

    @PostMapping("/{eventNumber}/question")
    @PreAuthorize("hasRole('ADMIN')")
    public QuestionDto addQuestionToEvent(@PathVariable String eventNumber, @RequestBody @Valid QuestionDto questionDto) {
        Question addedQuestion = manageEventService.addQuestionToEvent(eventNumber, questionMapper.mapToEntity(questionDto));
        return questionMapper.mapToDto(addedQuestion);
    }

    
    @GetMapping("/people/{eventNumber}")
    public List<PeopleDto> getEventsPeople(@PathVariable String eventNumber) {
        Set<People> eventsPeople = manageEventService.getEventsPeople(eventNumber);
        return peopleMapper.mapToDTo(new ArrayList<>(eventsPeople));
    }

    @GetMapping(value = "/{eventNumber}/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> qrgenQRCode(@PathVariable String eventNumber) throws Exception {
        Set<People>  getPeopleByEventNumber = manageEventService.getEventsPeople(eventNumber);
        String barcode = getPeopleByEventNumber.toString();
        return okResponse(QRGenBarcodeGenerator.generateQRCodeImage(barcode));
    }


    @PostMapping("/addPeople/{eventNumber}")
    public PeopleDto addPeopleToEvent(@PathVariable String eventNumber, @RequestBody @Valid PeopleDto peopleDto) {
        People addedPeople = manageEventService.addPeopleToEvent(eventNumber, peopleMapper.mapToEntity(peopleDto));
        return peopleMapper.mapToDto(addedPeople);
    }

   /*@PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> qrgenQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(QRGenBarcodeGenerator.generateQRCodeImage(barcode));
    } */

    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }






}
