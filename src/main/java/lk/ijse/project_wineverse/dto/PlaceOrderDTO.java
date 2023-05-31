package lk.ijse.project_wineverse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlaceOrderDTO {
    private String ordereditemcode;
    private Integer ordereditemqty;
}
