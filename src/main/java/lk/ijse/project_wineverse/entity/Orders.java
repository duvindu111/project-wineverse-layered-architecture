package lk.ijse.project_wineverse.entity;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Orders {
    private String order_id;
    private String cust_id;
    private boolean delivery;
    private LocalDate date;
    private Time time;
}
