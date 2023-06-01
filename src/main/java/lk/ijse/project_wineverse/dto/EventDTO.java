package lk.ijse.project_wineverse.dto;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventDTO {
    private String eventid;
    private String eventname;
    private String eventtype;
    private LocalDate eventdate;
    private LocalTime eventtime;
    private String empid;
}
