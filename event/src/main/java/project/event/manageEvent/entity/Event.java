package project.event.manageEvent.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.event.common.entity.BaseEntity;
import project.event.common.entity.Question;


import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "EVENT_SEQ")
public class Event extends BaseEntity {


    @Column(name = "EVENT_NUMBER", unique = true)
    private String eventNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "EVENT_ID")
    private Set<Question> questions;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "EVENT_PEOPLE",
            joinColumns = { @JoinColumn(name = "EVENT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "PEOPLE_ID") })
    private Set<People> eventParticipants = new HashSet<>();

    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "QUOTA")
    private Long quota;

    public boolean quota() {
        return eventParticipants.size() == quota;
    }

   public String getSize(){
        return "Number of participants of the " + eventName + " event: " + (eventParticipants.size()) + "\n";
    }

    public String getDayDuration(){
        return "The " + eventName + " event has a duration of " +
                "" + (endDate.getDate() - startDate.getDate()) + " days.";
    }

    public String toString(){
        return "Name of event attended: " + eventName + "\n" +
                "Number of the event attended: " + eventNumber;
    }


}