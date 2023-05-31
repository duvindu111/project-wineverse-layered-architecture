package lk.ijse.project_wineverse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    private String id;
    private String name;
    private String nic;
    private String dob;
    private String job;
    private String contact;
    private String address;
    private String email;
}
