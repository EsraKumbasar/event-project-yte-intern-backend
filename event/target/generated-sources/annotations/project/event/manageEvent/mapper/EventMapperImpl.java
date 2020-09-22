package project.event.manageEvent.mapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import project.event.manageEvent.dto.EventDto;
import project.event.manageEvent.dto.EventDto.EventDtoBuilder;
import project.event.manageEvent.entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-10T22:16:03+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDto mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDtoBuilder eventDto = EventDto.builder();

        eventDto.eventName( event.getEventName() );
        if ( event.getStartDate() != null ) {
            eventDto.startDate( LocalDateTime.ofInstant( event.getStartDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        if ( event.getEndDate() != null ) {
            eventDto.endDate( LocalDateTime.ofInstant( event.getEndDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        eventDto.eventNumber( event.getEventNumber() );
        eventDto.quota( event.getQuota() );

        return eventDto.build();
    }

    @Override
    public Event mapToEntity(EventDto eventDto) {
        if ( eventDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setEventNumber( eventDto.getEventNumber() );
        event.setEventName( eventDto.getEventName() );
        if ( eventDto.getStartDate() != null ) {
            event.setStartDate( Date.from( eventDto.getStartDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        if ( eventDto.getEndDate() != null ) {
            event.setEndDate( Date.from( eventDto.getEndDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        event.setQuota( eventDto.getQuota() );

        return event;
    }

    @Override
    public List<EventDto> mapToDto(List<Event> eventList) {
        if ( eventList == null ) {
            return null;
        }

        List<EventDto> list = new ArrayList<EventDto>( eventList.size() );
        for ( Event event : eventList ) {
            list.add( mapToDto( event ) );
        }

        return list;
    }

    @Override
    public List<Event> mapToEntity(List<EventDto> eventDtoList) {
        if ( eventDtoList == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( eventDtoList.size() );
        for ( EventDto eventDto : eventDtoList ) {
            list.add( mapToEntity( eventDto ) );
        }

        return list;
    }

    @Override
    public Set<EventDto> mapToDto(Set<Event> event) {
        if ( event == null ) {
            return null;
        }

        Set<EventDto> set = new HashSet<EventDto>( Math.max( (int) ( event.size() / .75f ) + 1, 16 ) );
        for ( Event event1 : event ) {
            set.add( mapToDto( event1 ) );
        }

        return set;
    }
}
