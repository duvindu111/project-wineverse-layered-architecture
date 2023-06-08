package lk.ijse.project_wineverse.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDTO {
    private String custid;
    private String custname;
    private Boolean delivery;
    private String date;
    private String time;
    private Double price;
}
