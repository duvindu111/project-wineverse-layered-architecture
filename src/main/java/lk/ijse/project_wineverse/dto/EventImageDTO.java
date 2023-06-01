package lk.ijse.project_wineverse.dto;

import lombok.*;

import java.io.InputStream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventImageDTO {
    String eventId;
    InputStream image;
}
