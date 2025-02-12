package lk.ijse.gdse.AirTicket.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BookingDto {
    private String bookingId;
    private String nic;
    private String mobileNumber;
    private String ticketId;
    private String planeId;
    private String seatId;
    private String destinationId;
    private double ticketPrice;
    private Date date;
    private String paymentMethod;
    private double balance;
    private double givingPrice;
    private String userName;
}
