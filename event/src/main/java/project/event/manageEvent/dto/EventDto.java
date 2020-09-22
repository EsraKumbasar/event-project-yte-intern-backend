package project.event.manageEvent.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;


import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Builder
public class EventDto {


    @Size(max = 255, message = "Event name can't be longer than 255 characters!")
    @NotEmpty(message = "Event name cannot be empty!")
    public final String eventName;

    @FutureOrPresent
    public final LocalDate startDate;

    @AssertTrue(message="\n" +
            "Start date cannot be later than end date")
    public boolean isEndDateTrue(){
        if (endDate.isAfter(startDate))
            return true;
        else
            return false;
    }
    public final LocalDate endDate;

    public final String eventNumber;

    public final Long quota;

    @JsonCreator
    public EventDto(@JsonProperty("eventName") String eventName,
                    @JsonProperty("startDate") LocalDate startDate,
                    @JsonProperty("endDate")LocalDate endDate,
                    @JsonProperty("eventNumber")String eventNumber,
                    @JsonProperty("quota") Long quota)
      {
          this.eventName = eventName;
          this.startDate = startDate;
          this.endDate = endDate;
          this.eventNumber = eventNumber;
          this.quota = quota;
    }
}
