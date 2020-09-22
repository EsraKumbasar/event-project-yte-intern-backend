package project.event.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionDto {

    public final String eventQuestion;

    public final String eventAnswer;

    @JsonCreator
    public QuestionDto(@JsonProperty("eventQuestion")String eventQuestion,
                       @JsonProperty("eventAnswer")String eventAnswer){

        this.eventQuestion = eventQuestion;
        this.eventAnswer = eventAnswer;
    }
}
