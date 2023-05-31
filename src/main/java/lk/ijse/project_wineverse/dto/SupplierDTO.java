package lk.ijse.project_wineverse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierDTO {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String email;
}
