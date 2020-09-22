package project.event.common.mapper;


import org.mapstruct.Mapper;
import project.event.common.dto.QuestionDto;
import project.event.common.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto mapToDto(Question question);

    Question mapToEntity(QuestionDto questionDto);

    List<QuestionDto> mapToDTo(List<Question> questionList);

    List<Question> mapToEntity(List<QuestionDto> questionDtoList);
}
