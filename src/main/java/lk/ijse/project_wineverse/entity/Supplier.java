package lk.ijse.project_wineverse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    private String supp_id;
    private String supp_name;
    private String supp_email;
    private String supp_contact;
    private String supp_address;
}
