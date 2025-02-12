package lk.ijse.gdse.AirTicket.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Seat {
    private String seatId;
    private String planeId;
    private String seatClass;
    private String availability;
}
