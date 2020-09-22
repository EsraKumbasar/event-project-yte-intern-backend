package project.event.manageEvent.mapper;

import org.mapstruct.Mapper;
import project.event.manageEvent.dto.EventDto;
import project.event.manageEvent.entity.Event;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto mapToDto(Event event);

    Event mapToEntity(EventDto eventDto);

    List<EventDto> mapToDto(List<Event> eventList);

    List<Event> mapToEntity(List<EventDto> eventDtoList);

    Set<EventDto> mapToDto(Set<Event> event);
}
