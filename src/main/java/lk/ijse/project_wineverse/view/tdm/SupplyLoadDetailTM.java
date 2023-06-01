package lk.ijse.project_wineverse.view.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplyLoadDetailTM {
    private String loadid;
    private String suppid;
    private String item_code;
    private Integer supp_qty;
    private String date;
    private String time;
    private Double price;
}
