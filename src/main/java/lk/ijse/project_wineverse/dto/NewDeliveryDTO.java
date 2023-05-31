package lk.ijse.project_wineverse.dto;

import lombok.*;

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
    private String duedate;
}
