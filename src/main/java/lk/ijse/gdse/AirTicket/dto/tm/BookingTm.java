package lk.ijse.gdse.AirTicket.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BookingTm {
    private String bookingId;
    private String seatId;
    private String planeId;
    private String destinationId;
    private String ticketId;

}
