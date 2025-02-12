package lk.ijse.gdse.AirTicket.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Plane {
    private String planeId;
    private String planeName;
    private String flightClass;
    private String seatCount;
}
