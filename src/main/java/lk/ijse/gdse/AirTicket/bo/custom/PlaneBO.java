package lk.ijse.gdse.AirTicket.bo.custom;

import lk.ijse.gdse.AirTicket.bo.SuperBO;
import lk.ijse.gdse.AirTicket.dto.PlaneDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaneBO extends SuperBO {
    public String getNextId() throws SQLException ;

    public boolean save(PlaneDto planeDto) throws SQLException ;

    public ArrayList<PlaneDto> getAll() throws SQLException ;

    public boolean update(PlaneDto planeDto) throws SQLException;

    public boolean delete(String planeId) throws SQLException ;

    public ArrayList<String> getAllIds() throws SQLException ;

    public PlaneDto findById(String selectedPlaneId) throws SQLException;

    public PlaneDto findByName(String name) throws SQLException;
}
