package lk.ijse.project_wineverse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeliveryTM {
    private String orderid;
    private String delid;
    private String delstatus;
    private String duedate;
    private String deldate;
    private String location;
    private String empid;
}
