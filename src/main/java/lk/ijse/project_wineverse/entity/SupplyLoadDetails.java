package lk.ijse.project_wineverse.entity;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplyLoadDetails {
    private String load_id;
    private String supp_id;
    private String item_code;
    private int supp_qty;
    private LocalDate date;
    private Time time;
    private Double price;
}
