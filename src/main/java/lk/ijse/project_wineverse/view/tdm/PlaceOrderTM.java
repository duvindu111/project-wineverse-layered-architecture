package lk.ijse.project_wineverse.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlaceOrderTM {
    private String itemcode;
    private String itemname;
    private Double unitprice;
    private String category;
    private Integer quantity;
    private Double total;
    private Button removebtn;
}
