package lk.ijse.project_wineverse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private String order_id;
    private String item_code;
    private int order_qty;

    public OrderDetail(String ordereditemcode, Integer ordereditemqty) {
        this.item_code=ordereditemcode;
        this.order_qty=ordereditemqty;
    }
}
