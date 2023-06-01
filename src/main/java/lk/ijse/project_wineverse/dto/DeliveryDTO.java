package lk.ijse.project_wineverse.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeliveryDTO {
    private String delid;
    private String delsts;
    private String loc;
    private LocalDate deldate;
    private LocalDate duedate;
    private String ordid;
    private String empid;
}
