package project.event.manageEvent.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import project.event.manageEvent.dto.PeopleDto;
import project.event.manageEvent.dto.PeopleDto.PeopleDtoBuilder;
import project.event.manageEvent.entity.People;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-10T22:16:03+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
@Component
public class PeopleMapperImpl implements PeopleMapper {

    @Override
    public PeopleDto mapToDto(People people) {
        if ( people == null ) {
            return null;
        }

        PeopleDtoBuilder peopleDto = PeopleDto.builder();

        peopleDto.peopleName( people.getPeopleName() );
        peopleDto.peopleSurname( people.getPeopleSurname() );
        peopleDto.peopleTCNo( people.getPeopleTCNo() );
        peopleDto.peopleEmail( people.getPeopleEmail() );

        return peopleDto.build();
    }

    @Override
    public People mapToEntity(PeopleDto peopleDto) {
        if ( peopleDto == null ) {
            return null;
        }

        People people = new People();

        people.setPeopleName( peopleDto.getPeopleName() );
        people.setPeopleSurname( peopleDto.getPeopleSurname() );
        people.setPeopleTCNo( peopleDto.getPeopleTCNo() );
        people.setPeopleEmail( peopleDto.getPeopleEmail() );

        return people;
    }

    @Override
    public List<PeopleDto> mapToDTo(List<People> peopleList) {
        if ( peopleList == null ) {
            return null;
        }

        List<PeopleDto> list = new ArrayList<PeopleDto>( peopleList.size() );
        for ( People people : peopleList ) {
            list.add( mapToDto( people ) );
        }

        return list;
    }

    @Override
    public List<People> mapToEntity(List<PeopleDto> peopleDtoList) {
        if ( peopleDtoList == null ) {
            return null;
        }

        List<People> list = new ArrayList<People>( peopleDtoList.size() );
        for ( PeopleDto peopleDto : peopleDtoList ) {
            list.add( mapToEntity( peopleDto ) );
        }

        return list;
    }
}
