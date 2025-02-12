package lk.ijse.gdse.AirTicket.dao.custom.impl;

import lk.ijse.gdse.AirTicket.dao.custom.SeatDAO;
import lk.ijse.gdse.AirTicket.dto.SeatDto;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.entity.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatDAOImpl implements SeatDAO {

    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT seat_id FROM seats ORDER BY seat_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("S%03d", newIdIndex);
        }
        return "S001";
    }

    public boolean save(Seat seat) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO seats VALUES (?, ?, ?, ?)",
                seat.getSeatId(),
                seat.getPlaneId(),
                seat.getSeatClass(),
                seat.getAvailability()
        );
    }

    public ArrayList<Seat> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM seats");

        ArrayList<Seat> seats = new ArrayList<>();

        while (rst.next()) {
            Seat seat = new Seat(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            seats.add(seat);
        }
        return seats;
    }

    public boolean update(Seat seat) throws SQLException {
        return CrudUtil.execute(
                "UPDATE seats SET plane_id = ?, seat_class = ?, availability = ? WHERE seat_id = ?",
                seat.getPlaneId(),
                seat.getSeatClass(),
                seat.getAvailability(),
                seat.getSeatId()
        );
    }

    public boolean delete(String seatId) throws SQLException {
        return CrudUtil.execute("DELETE FROM seats WHERE seat_id = ?", seatId);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT seat_id FROM seats");

        ArrayList<String> seatIds = new ArrayList<>();

        while (rst.next()) {
            seatIds.add(rst.getString(1));
        }
        return seatIds;
    }

    public Seat findById(String selectedSeatId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM seats WHERE seat_id = ?", selectedSeatId);

        if (rst.next()) {
            return new Seat(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public Seat findByName(String selectedId) throws SQLException {
        return null;
    }

    public ArrayList<String> getSeatsByPlaneId(String planeId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT seat_id FROM seats WHERE plane_id = ?", planeId);

        ArrayList<String> seatIds = new ArrayList<>();

        while (rst.next()) {
            seatIds.add(rst.getString(1));
        }
        return seatIds;
    }
}
