package lk.ijse.project_wineverse.dto.tm;

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
