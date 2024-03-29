package lk.ijse.project_wineverse.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddSupplyLoadTM {
    private String itemcode;
    private String itemname;
    private String category;
    private Integer quantity;
    private Button removebtn;
}
