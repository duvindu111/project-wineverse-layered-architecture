package lk.ijse.project_wineverse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SalaryTM {
    private String empid;
    private String slryid;
    private Double slryamount;
    private Double ot;
    private String paymethod;
}
