package project.event.manageEvent.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import project.event.manageEvent.validation.TcKimlikNo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Builder
public class PeopleDto {

    @NotEmpty(message = "People name cannot be empty!")
    @Size(max = 100, message = "User name can't be longer than 100 characters!")
    public final String peopleName;

    @NotEmpty(message = "People surname cannot be empty!")
    @Size(max = 100, message = "User surname can't be longer than 100 characters!")
    public final String peopleSurname;

    @Size(min = 11, max = 11, message = "TC Kimlik no must be 11 characters long!")
    public final String peopleTCNo;


    @Email
    public final String peopleEmail;

    @JsonCreator
    public PeopleDto(@JsonProperty("peopleName") String peopleName,
                    @JsonProperty("peopleSurname") String peopleSurname,
                    @JsonProperty("peopleTCNo") String peopleTCNo,
                     @JsonProperty("peopleEmail") String peopleEmail)
    {
        this.peopleName = peopleName;
        this.peopleSurname = peopleSurname;
        this.peopleTCNo = peopleTCNo;
        this.peopleEmail = peopleEmail;
    }
}
