package lk.ijse.project_wineverse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {
    private String code;
    private String name;
    private String unitprice;
    private String category;
    private String quantity;
}
