package lk.ijse.project_wineverse.entity;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Event {
    private String event_id;
    private String event_name;
    private String event_type;
    private LocalDate event_date;
    private LocalTime event_time;
    private String emp_id;
}
