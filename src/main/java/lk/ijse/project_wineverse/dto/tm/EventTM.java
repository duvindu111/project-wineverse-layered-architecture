package lk.ijse.project_wineverse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventTM {
    private String empid;
    private String eventid;
    private String eventname;
    private String eventtype;
    private String eventdate;
    private String eventtime;
}

