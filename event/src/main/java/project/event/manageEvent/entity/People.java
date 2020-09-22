package project.event.manageEvent.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.event.common.entity.BaseEntity;
import project.event.common.entity.Question;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "idgen", sequenceName = "PEOPLE_SEQ")
@AllArgsConstructor
@NoArgsConstructor
public class People extends BaseEntity {

    @OneToMany
    @JoinColumn(name = "PEOPLE_ID")
    private Set<Question> questions;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "eventParticipants")
    private Set<Event> events = new HashSet<>();

    @Column(name = "PEOPLE_NAME")
    private String peopleName;

    @Column(name = "PEOPLE_SURNAME")
    private String peopleSurname;

    @Column(name = "PEOPLE_TCNO", unique = true)
    private String peopleTCNo;


    @Column(name = "PEOPLE_EMAIL", unique = true)
    private String peopleEmail;

    public String toString(){
        return "Participant's Name: " + peopleName + "\n" +
                "Participant's Surname: " + peopleSurname + "\n" +
                "Participant's T.C. ID Number: " + peopleTCNo + "\n" +
                "Participant's E-mail Address: " + peopleEmail + "\n" +
                getEvents().toString();

    }


}