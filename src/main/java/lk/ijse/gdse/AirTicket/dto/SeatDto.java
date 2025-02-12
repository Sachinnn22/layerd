package lk.ijse.gdse.AirTicket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SeatDto {
    private String seatId;
    private String planeId;
    private String seatClass;
    private String availability;
}
