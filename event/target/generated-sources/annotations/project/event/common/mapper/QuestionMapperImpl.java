package project.event.common.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import project.event.common.dto.QuestionDto;
import project.event.common.dto.QuestionDto.QuestionDtoBuilder;
import project.event.common.entity.Question;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-10T22:16:03+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public QuestionDto mapToDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDtoBuilder questionDto = QuestionDto.builder();

        questionDto.eventQuestion( question.getEventQuestion() );
        questionDto.eventAnswer( question.getEventAnswer() );

        return questionDto.build();
    }

    @Override
    public Question mapToEntity(QuestionDto questionDto) {
        if ( questionDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setEventQuestion( questionDto.getEventQuestion() );
        question.setEventAnswer( questionDto.getEventAnswer() );

        return question;
    }

    @Override
    public List<QuestionDto> mapToDTo(List<Question> questionList) {
        if ( questionList == null ) {
            return null;
        }

        List<QuestionDto> list = new ArrayList<QuestionDto>( questionList.size() );
        for ( Question question : questionList ) {
            list.add( mapToDto( question ) );
        }

        return list;
    }

    @Override
    public List<Question> mapToEntity(List<QuestionDto> questionDtoList) {
        if ( questionDtoList == null ) {
            return null;
        }

        List<Question> list = new ArrayList<Question>( questionDtoList.size() );
        for ( QuestionDto questionDto : questionDtoList ) {
            list.add( mapToEntity( questionDto ) );
        }

        return list;
    }
}
