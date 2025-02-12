package lk.ijse.gdse.AirTicket.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class DestinationTm {
    private String destinationId;
    private String destinationName;
    private String distance;
}
