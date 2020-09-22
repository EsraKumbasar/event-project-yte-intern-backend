package project.event.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.event.common.entity.BaseEntity;
import project.event.manageEvent.entity.Event;
import project.event.manageEvent.entity.People;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "QUESTION_SEQ")
public class Question extends BaseEntity {

    @ManyToOne
    private People people;

    @ManyToOne
    private Event event;

    @Column(name = "EVENT_QUESTION")
    private String eventQuestion;

    @Column(name = "EVENT_ANSWER")
    private String eventAnswer;



}
