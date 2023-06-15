package lk.ijse.project_wineverse.dto;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

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
    private List<PlaceSupplyLoadDTO> placeSupplyLoadDTOList;

    public SupplyLoadDetailsDTO(String loadid, String suppid, String totalprice) {
        this.load_id=loadid;
        this.supp_id=suppid;
        this.price= Double.valueOf(totalprice);
    }

    public SupplyLoadDetailsDTO(String loadid, String suppid, String totalprice, List<PlaceSupplyLoadDTO> placeSupplyLoadList) {
        this.load_id=loadid;
        this.supp_id=suppid;
        this.price= Double.valueOf(totalprice);
        this.placeSupplyLoadDTOList=placeSupplyLoadList;
    }

    public SupplyLoadDetailsDTO(String load_id, String supp_id, String item_code, int supp_qty, LocalDate date, Time time, Double price) {
        this.load_id = load_id;
        this.supp_id = supp_id;
        this.item_code = item_code;
        this.supp_qty = supp_qty;
        this.date = date;
        this.time = time;
        this.price = price;
    }
}
