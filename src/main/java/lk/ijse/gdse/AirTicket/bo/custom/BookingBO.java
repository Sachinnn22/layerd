package lk.ijse.gdse.AirTicket.bo.custom;

import lk.ijse.gdse.AirTicket.bo.SuperBO;
import lk.ijse.gdse.AirTicket.dto.BookingDto;
import lk.ijse.gdse.AirTicket.entity.Booking;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingBO extends SuperBO {
    String getNextId() throws SQLException;
    boolean save(BookingDto bookingDto) throws SQLException;
    ArrayList<BookingDto> getAll() throws SQLException;
    boolean update(BookingDto bookingDto) throws SQLException;
    Booking findById(String bookingId) throws SQLException;
    boolean delete(String bookingId) throws SQLException;
}
