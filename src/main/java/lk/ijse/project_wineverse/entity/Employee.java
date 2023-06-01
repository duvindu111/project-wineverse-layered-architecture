package lk.ijse.project_wineverse.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private String emp_id;
    private String emp_name;
    private LocalDate emp_dob;
    private String emp_address;
    private String emp_contact;
    private String emp_email;
    private String emp_job_title;
    private String emp_nic;
}
