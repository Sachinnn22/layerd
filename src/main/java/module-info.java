module lk.ijse.gdse.AirTicket {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires java.desktop;

    opens lk.ijse.gdse.AirTicket.dto.tm to javafx.base;
    opens lk.ijse.gdse.AirTicket.controller to javafx.fxml;
    exports lk.ijse.gdse.AirTicket;
}