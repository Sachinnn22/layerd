package lk.ijse.gdse.AirTicket.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Ticket {
    private String ticketId;
    private String destinationId;
    private String PlaneId;
    private String ticketClass;
    private Double ticketCost;
}
