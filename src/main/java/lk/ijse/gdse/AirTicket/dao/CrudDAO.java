package lk.ijse.gdse.AirTicket.dao;

import lk.ijse.gdse.AirTicket.dto.PassengerDto;
import lk.ijse.gdse.AirTicket.dto.PlaneDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO  {

    public String getNextId() throws SQLException;

    public boolean save(T dto) throws SQLException ;

    public ArrayList<T> getAll() throws SQLException ;

    public boolean update(T dto) throws SQLException ;

    public boolean delete(String id) throws SQLException ;

    public ArrayList<String> getAllIds() throws SQLException ;

    public T findById(String selectedId) throws SQLException;

    public T findByName(String selectedId) throws SQLException;
}
