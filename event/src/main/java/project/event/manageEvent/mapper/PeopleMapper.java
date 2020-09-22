package project.event.manageEvent.mapper;

import org.mapstruct.Mapper;
import project.event.manageEvent.dto.PeopleDto;
import project.event.manageEvent.entity.People;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeopleMapper {

    PeopleDto mapToDto(People people);

    People mapToEntity(PeopleDto peopleDto);

    List<PeopleDto> mapToDTo(List<People> peopleList);

    List<People> mapToEntity(List<PeopleDto> peopleDtoList);
}
