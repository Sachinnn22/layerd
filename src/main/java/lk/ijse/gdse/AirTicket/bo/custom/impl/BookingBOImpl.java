package lk.ijse.gdse.AirTicket.bo.custom.impl;

import lk.ijse.gdse.AirTicket.bo.custom.BookingBO;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.dao.FactoryDAO;
import lk.ijse.gdse.AirTicket.dao.custom.BookingDAO;
import lk.ijse.gdse.AirTicket.dao.custom.PlaneDAO;
import lk.ijse.gdse.AirTicket.dao.custom.SeatDAO;
import lk.ijse.gdse.AirTicket.db.DBConnection;
import lk.ijse.gdse.AirTicket.dto.BookingDto;
import lk.ijse.gdse.AirTicket.dto.PlaneDto;
import lk.ijse.gdse.AirTicket.entity.Booking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBOImpl implements BookingBO {

    BookingDAO bookingDAO = (BookingDAO) FactoryDAO.getInstance().getDao(FactoryDAO.DaoType.BOOKING);
    SeatDAO seatDAO = (SeatDAO) FactoryDAO.getInstance().getDao(FactoryDAO.DaoType.SEAT);

    @Override
    public String getNextId() throws SQLException {
       return bookingDAO.getNextId();
    }

    // Place a booking
    @Override
    public boolean save(BookingDto bookingDto) throws SQLException {
//      return bookingDAO.save(new Booking(bookingDto.getBookingId(), bookingDto.getNic(), bookingDto.getMobileNumber(), bookingDto.getTicketId(), bookingDto.getPlaneId(), bookingDto.getSeatId(), bookingDto.getDestinationId(), bookingDto.getTicketPrice(), bookingDto.getDate(), bookingDto.getPaymentMethod(), bookingDto.getBalance(), bookingDto.getGivingPrice(), bookingDto.getUserName()));
        System.out.println(bookingDto);

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO bookings VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    bookingDto.getBookingId(),
                    bookingDto.getNic(),
                    bookingDto.getMobileNumber(),
                    bookingDto.getTicketId(),
                    bookingDto.getPlaneId(),
                    bookingDto.getSeatId(),
                    bookingDto.getDestinationId(),
                    bookingDto.getTicketPrice(),
                    bookingDto.getDate(),
                    bookingDto.getPaymentMethod(),
                    bookingDto.getBalance(),
                    bookingDto.getGivingPrice(),
                    bookingDto.getUserName()
            );

            System.out.println(isSaved);

            if (isSaved) {
                boolean seatUpdated = CrudUtil.execute(
                        "UPDATE seats SET availability = ? WHERE seat_id = ?",
                        "NO",
                        bookingDto.getSeatId()
                );
                if (seatUpdated) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // Retrieve all bookings
    @Override
    public ArrayList<BookingDto> getAll() throws SQLException {
        ArrayList<BookingDto> bookingDtos = new ArrayList<>();
        ArrayList<Booking> bookings = bookingDAO.getAll();
        for (Booking booking : bookings) {
            BookingDto bookingDto = new BookingDto(booking.getBookingId(), booking.getNic(), booking.getMobileNumber(), booking.getTicketId(), booking.getPlaneId(), booking.getSeatId(), booking.getDestinationId(), booking.getTicketPrice(), booking.getDate(), booking.getPaymentMethod(), booking.getBalance(), booking.getGivingPrice(), booking.getUserName());
            bookingDtos.add(bookingDto);
        }
        return bookingDtos;
    }

    @Override
    public boolean update(BookingDto bookingDto) throws SQLException {
        return bookingDAO.update(new Booking(bookingDto.getBookingId(), bookingDto.getNic(), bookingDto.getMobileNumber(), bookingDto.getTicketId(), bookingDto.getPlaneId(), bookingDto.getSeatId(), bookingDto.getDestinationId(), bookingDto.getTicketPrice(), bookingDto.getDate(), bookingDto.getPaymentMethod(), bookingDto.getBalance(), bookingDto.getGivingPrice(), bookingDto.getUserName()));
    }

    // Find a booking by ID
    @Override
    public Booking findById(String bookingId) throws SQLException {
        return bookingDAO.findById(bookingId);
    }

    // Delete a booking
    @Override
    public boolean delete(String bookingId) throws SQLException {
        return bookingDAO.delete(bookingId);
    }

}
