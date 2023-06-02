package lk.ijse.project_wineverse.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NewDeliveryDTO {
    private String orderid;
    private String delid;
    private String location;
    private String empid;
    private LocalDate duedate;
}
