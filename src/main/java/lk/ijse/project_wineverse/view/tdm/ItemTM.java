package lk.ijse.project_wineverse.view.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemTM {
    private String code;
    private String name;
    private Double unitprice;
    private String category;
    private Integer quantity;
}
