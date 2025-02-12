package lk.ijse.gdse.AirTicket.dao.custom.impl;

import lk.ijse.gdse.AirTicket.dao.custom.BookingDAO;
import lk.ijse.gdse.AirTicket.dto.BookingDto;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.entity.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {

    // Generate the next booking ID
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT booking_id FROM bookings ORDER BY booking_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int newIdIndex = Integer.parseInt(substring) + 1;
            return String.format("B%03d", newIdIndex);
        }
        return "B001";
    }

    // Place a booking
    public boolean save(Booking entity) throws SQLException {
        return false;
    }

    // Retrieve all bookings
    public ArrayList<Booking> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM bookings");

        ArrayList<Booking> bookingList = new ArrayList<>();
        while (rst.next()) {
            Booking entity = new Booking(
                    rst.getString("booking_id"),
                    rst.getString("nic"),
                    rst.getString("mobile_number"),
                    rst.getString("ticket_id"),
                    rst.getString("plane_id"),
                    rst.getString("seat_id"),
                    rst.getString("destination_id"),
                    rst.getDouble("ticket_price"),
                    rst.getDate("date"),
                    rst.getString("payment_method"),
                    rst.getDouble("balance"),
                    rst.getDouble("giving_price"),
                    rst.getString("user_name")
            );
            bookingList.add(entity);
        }
        return bookingList;
    }

    @Override
    public boolean update(Booking dto) throws SQLException {
        return false;
    }

    // Find a booking by ID
    public Booking findById(String bookingId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM bookings WHERE booking_id=?", bookingId);

        if (rst.next()) {
            return new Booking(
                    rst.getString("booking_id"),
                    rst.getString("nic"),
                    rst.getString("mobile_number"),
                    rst.getString("ticket_id"),
                    rst.getString("plane_id"),
                    rst.getString("seat_id"),
                    rst.getString("destination_id"),
                    rst.getDouble("ticket_price"),
                    rst.getDate("date"),
                    rst.getString("payment_method"),
                    rst.getDouble("balance"),
                    rst.getDouble("giving_price"),
                    rst.getString("user_name")
            );
        }
        return null;
    }

    public Booking findByName(String bookingName) throws SQLException {
        return null;
    }

    // Delete a booking
    public boolean delete(String bookingId) throws SQLException {
        return CrudUtil.execute("DELETE FROM bookings WHERE booking_id=?", bookingId);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }
}
