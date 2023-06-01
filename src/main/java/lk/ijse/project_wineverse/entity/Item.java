package lk.ijse.project_wineverse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    private String item_code;
    private String item_name;
    private Double unit_price;
    private String item_category;
    private int item_qty;
}
