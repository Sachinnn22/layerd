package lk.ijse.gdse.AirTicket.dao.custom.impl;

import lk.ijse.gdse.AirTicket.dao.custom.DestinationDAO;
import lk.ijse.gdse.AirTicket.dto.DestinationDTO;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.dto.PassengerDto;
import lk.ijse.gdse.AirTicket.entity.Destination;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DestinationDAOImpl implements DestinationDAO {


    // Method to get the next available destination ID
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT destination_id FROM destination ORDER BY destination_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String idNumberPart = lastId.substring(1); // Assuming IDs like "D001"
            int nextIdNumber = Integer.parseInt(idNumberPart) + 1;
            return String.format("D%03d", nextIdNumber); // Format as "D001", "D002", etc.
        }
        return "D001"; // Starting ID if no entries exist
    }

    // Method to save a new destination
    public boolean save(Destination destination) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO destination VALUES (?, ?, ?)",
                destination.getDestinationId(),
                destination.getDestinationName(),
                destination.getDistance()
        );
    }

    // Method to retrieve all destinations
    public ArrayList<Destination> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM destination");

        ArrayList<Destination> destinations = new ArrayList<>();
        while (rst.next()) {
            Destination destination = new Destination(
                    rst.getString("destination_id"),
                    rst.getString("destination_name"),
                    rst.getString("distance")
            );
            destinations.add(destination);
        }
        return destinations;
    }

    // Method to update an existing destination
    public boolean update(Destination destination) throws SQLException {
        return CrudUtil.execute(
                "UPDATE destination SET destination_name=?, distance=? WHERE destination_id=?",
                destination.getDestinationName(),
                destination.getDistance(),
                destination.getDestinationId()
        );
    }

    // Method to delete a destination by ID
    public boolean delete(String destinationId) throws SQLException {
        return CrudUtil.execute("DELETE FROM destination WHERE destination_id=?", destinationId);
    }

    // Method to get a list of all destination IDs
    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT destination_id FROM destination");

        ArrayList<String> destinationIds = new ArrayList<>();
        while (rst.next()) {
            destinationIds.add(rst.getString(1));
        }
        return destinationIds;
    }

    // Method to find a destination by ID
    public Destination findById(String selectedDestinationId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM destination WHERE destination_id=?", selectedDestinationId);

        if (rst.next()) {
            return new Destination(
                    rst.getString("destination_id"),
                    rst.getString("destination_name"),
                    rst.getString("distance")
            );
        }
        return null;
    }

    public Destination findByName(String selectedDestinationName) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM destination WHERE destination_name=?", selectedDestinationName);

        if (rst.next()) {
            return new Destination(
                    rst.getString("destination_id"),
                    rst.getString("destination_name"),
                    rst.getString("distance")
            );
        }
        return null;
    }
}
