package lk.ijse.project_wineverse.dto;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplyLoadDetailsDTO {
    private String load_id;
    private String supp_id;
    private String item_code;
    private int supp_qty;
    private LocalDate date;
    private Time time;
    private Double price;

    public SupplyLoadDetailsDTO(String loadid, String suppid, String totalprice) {
        this.load_id=loadid;
        this.supp_id=suppid;
        this.price= Double.valueOf(totalprice);
    }
}
