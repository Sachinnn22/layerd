package lk.ijse.gdse.AirTicket.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TicketTm {
    private String ticketId;
    private String destinationId;
    private String planeId;
    private String ticketClass;
    private Double ticketCost;

}
