package lk.ijse.project_wineverse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SignUpDTO {
    private String username;
    private String password;
    private String job_title;
    private String email;
}
