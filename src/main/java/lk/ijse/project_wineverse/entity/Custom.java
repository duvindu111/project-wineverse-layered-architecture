package lk.ijse.project_wineverse.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Custom {
    private String order_id;
    private String item_code;
    private int order_qty;
    private String item_name;
    private Double unit_price;
    private String item_category;
    private int item_qty;
    private String cust_id;
    private Boolean delivery;
    private LocalDate order_date;
    private LocalTime order_time;
    private Double order_payment;
    private String cust_name;
    private String cust_email;
    private String cust_contact;
}
