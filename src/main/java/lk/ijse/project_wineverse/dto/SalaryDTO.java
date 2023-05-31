package lk.ijse.project_wineverse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SalaryDTO {
    private String empid;
    private String slryid;
    private Double slryamount;
    private Double ot;
    private String paymethod;
}
