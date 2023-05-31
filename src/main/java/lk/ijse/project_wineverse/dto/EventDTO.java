package lk.ijse.project_wineverse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventDTO {
    private String eventid;
    private String eventname;
    private String eventtype;
    private String eventdate;
    private String eventtime;
    private String empid;
}
