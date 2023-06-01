package lk.ijse.project_wineverse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Salary {
    private String emp_id;
    private String salary_id;
    private Double salary_amount;
    private Double OT;
    private String payment_method;
}
