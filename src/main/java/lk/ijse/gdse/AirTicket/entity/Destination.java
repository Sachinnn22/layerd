package lk.ijse.gdse.AirTicket.entity;

import lombok.*;

/**
 * --------------------------------------------
 * Author: R.I.B. Shamodha Sahan Rathnamalala
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.live
 * --------------------------------------------
 * Created: 10/11/2024 1:42 PM
 * Project: supermarketfx-71
 * --------------------------------------------
 **/

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
public class Destination {
    private String destinationId;
    private String destinationName;
    private String distance;
}
