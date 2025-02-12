package lk.ijse.gdse.AirTicket.bo.custom.impl;

import lk.ijse.gdse.AirTicket.bo.custom.SeatBO;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.dao.custom.SeatDAO;
import lk.ijse.gdse.AirTicket.dao.custom.impl.SeatDAOImpl;
import lk.ijse.gdse.AirTicket.dto.SeatDto;
import lk.ijse.gdse.AirTicket.entity.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatBOImpl implements SeatBO {

    SeatDAO seatDAO = new SeatDAOImpl();

    public String getNextId() throws SQLException {
        return seatDAO.getNextId();
    }

    public boolean save(SeatDto seatDto) throws SQLException {
        return seatDAO.save(new Seat(seatDto.getSeatId(), seatDto.getPlaneId(), seatDto.getSeatClass(), seatDto.getAvailability()));
    }

    public ArrayList<SeatDto> getAll() throws SQLException {
        ArrayList<SeatDto> seatDtos = new ArrayList<>();
        ArrayList<Seat> seats = seatDAO.getAll();

        for (Seat seat : seats) {
            SeatDto seatDto = new SeatDto(
                    seat.getSeatId(),
                    seat.getPlaneId(),
                    seat.getSeatClass(),
                    seat.getAvailability()
            );
            seatDtos.add(seatDto);
        }
       return seatDtos;
    }

    public boolean update(SeatDto seatDto) throws SQLException {
        return seatDAO.update(new Seat(seatDto.getSeatId(), seatDto.getPlaneId(), seatDto.getSeatClass(), seatDto.getAvailability()));
    }

    public boolean delete(String seatId) throws SQLException {
       return seatDAO.delete(seatId);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        return seatDAO.getAllIds();
    }

    public SeatDto findById(String selectedSeatId) throws SQLException {
        Seat seat = seatDAO.findById(selectedSeatId);
        return new SeatDto(seat.getSeatId(), seat.getPlaneId(), seat.getSeatClass(), seat.getAvailability());
    }

    public ArrayList<String> getSeatsByPlaneId(String planeId) throws SQLException {
        return seatDAO.getSeatsByPlaneId(planeId);
    }
}
