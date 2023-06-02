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
public class Orders {
    private String order_id;
    private String cust_id;
    private Boolean delivery;
    private LocalDate order_date;
    private LocalTime order_time;
    private Double order_payment;
}
