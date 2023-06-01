package lk.ijse.project_wineverse.entity;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Delivery {
    private String delivery_id;
    private String delivery_status;
    private String location;
    private LocalDate delivered_date;
    private LocalDate due_date;
    private String order_id;
    private String emp_id;
}
