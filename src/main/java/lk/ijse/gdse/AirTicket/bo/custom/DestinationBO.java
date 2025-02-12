package lk.ijse.gdse.AirTicket.bo.custom;

import lk.ijse.gdse.AirTicket.bo.SuperBO;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.dto.DestinationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DestinationBO extends SuperBO {
    public String getNextId() throws SQLException ;

    // Method to retrieve all destinations
    public ArrayList<DestinationDTO> getAll() throws SQLException ;

    // Method to update an existing destination
    public boolean update(DestinationDTO destinationDTO) throws SQLException ;

    // Method to delete a destination by ID
    public boolean delete(String destinationId) throws SQLException ;

    // Method to get a list of all destination IDs
    public ArrayList<String> getAllIds() throws SQLException ;

    public boolean save(DestinationDTO destinationDTO) throws SQLException ;

    // Method to find a destination by ID
    public DestinationDTO findById(String selectedDestinationId) throws SQLException ;

    public DestinationDTO findByName(String selectedDestinationName) throws SQLException ;
}
