package lk.ijse.project_wineverse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String username;
    private String password;
    private String user_job_title;
    private String email;
}
