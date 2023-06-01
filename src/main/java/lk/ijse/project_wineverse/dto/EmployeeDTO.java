package lk.ijse.project_wineverse.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    private String id;
    private String name;
    private String nic;
    private LocalDate dob;
    private String job;
    private String contact;
    private String address;
    private String email;
}
