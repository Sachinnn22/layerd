package lk.ijse.gdse.AirTicket.bo.custom;

import lk.ijse.gdse.AirTicket.bo.SuperBO;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.dto.PassengerDto;
import lk.ijse.gdse.AirTicket.entity.Passenger;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PassangerBO extends SuperBO {

    public boolean save(PassengerDto passengerDto) throws SQLException ;
    public String getNextId() throws SQLException;
    public ArrayList<PassengerDto> getAll() throws SQLException;
    public boolean update(PassengerDto entity) throws SQLException;
    public boolean delete(String planeId) throws SQLException;
    public ArrayList<String> getAllIds() throws SQLException;
    public PassengerDto findById(String selectedPlaneId) throws SQLException;
    public PassengerDto findByName(String selectedId) throws SQLException;
}