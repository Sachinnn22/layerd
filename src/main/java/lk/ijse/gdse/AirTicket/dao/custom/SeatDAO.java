package lk.ijse.gdse.AirTicket.dao.custom;

import lk.ijse.gdse.AirTicket.dao.CrudDAO;
import lk.ijse.gdse.AirTicket.dto.SeatDto;
import lk.ijse.gdse.AirTicket.entity.Seat;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SeatDAO extends CrudDAO <Seat> {
    ArrayList<String> getSeatsByPlaneId(String planeId) throws SQLException;
}
