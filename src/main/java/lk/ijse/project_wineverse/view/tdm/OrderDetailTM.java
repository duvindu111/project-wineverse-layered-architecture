package lk.ijse.project_wineverse.view.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailTM {
    private String orderid;
    private String itemcode;
    private String itemname;
    private String qty;
}
