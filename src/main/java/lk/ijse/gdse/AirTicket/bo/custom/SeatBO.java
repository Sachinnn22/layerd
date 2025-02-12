package lk.ijse.gdse.AirTicket.bo.custom;

import lk.ijse.gdse.AirTicket.bo.SuperBO;
import lk.ijse.gdse.AirTicket.dto.SeatDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SeatBO extends SuperBO {


    public String getNextId() throws SQLException ;

    public boolean save(SeatDto seatDto) throws SQLException ;

    public ArrayList<SeatDto> getAll() throws SQLException ;

    public boolean update(SeatDto seatDto) throws SQLException ;

    public boolean delete(String seatId) throws SQLException ;

    public ArrayList<String> getAllIds() throws SQLException ;

    public SeatDto findById(String selectedSeatId) throws SQLException ;

    public ArrayList<String> getSeatsByPlaneId(String planeId) throws SQLException ;
}
