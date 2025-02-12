package lk.ijse.gdse.AirTicket.bo.custom.impl;

import lk.ijse.gdse.AirTicket.bo.custom.DestinationBO;
import lk.ijse.gdse.AirTicket.dao.custom.DestinationDAO;
import lk.ijse.gdse.AirTicket.dao.custom.impl.DestinationDAOImpl;
import lk.ijse.gdse.AirTicket.dto.DestinationDTO;
import lk.ijse.gdse.AirTicket.entity.Destination;

import java.sql.SQLException;
import java.util.ArrayList;

public class DestinationBOImpl implements DestinationBO {

    DestinationDAO destinationDAO = new DestinationDAOImpl();

    @Override
    public String getNextId() throws SQLException {
        return destinationDAO.getNextId();
    }

    @Override
    // Method to retrieve all destinations
    public ArrayList<DestinationDTO> getAll() throws SQLException {
        ArrayList<DestinationDTO> destinationDTOS = new ArrayList<>();
        ArrayList<Destination> destinations = destinationDAO.getAll();
        for (Destination destination : destinations) {
            DestinationDTO dto = new DestinationDTO(destination.getDestinationId(), destination.getDestinationName(), destination.getDistance());
            destinationDTOS.add(dto);
        }
        return destinationDTOS;
    }

    @Override
    // Method to update an existing destination
    public boolean update(DestinationDTO destinationDTO) throws SQLException {
        return destinationDAO.update(new Destination(destinationDTO.getDestinationId(), destinationDTO.getDestinationName(), destinationDTO.getDistance()));
    }

    @Override
    // Method to delete a destination by ID
    public boolean delete(String destinationId) throws SQLException {
        return destinationDAO.delete(destinationId);
    }

    @Override
    // Method to get a list of all destination IDs
    public ArrayList<String> getAllIds() throws SQLException {
        return destinationDAO.getAllIds();
    }

    @Override
    public boolean save(DestinationDTO destinationDTO) throws SQLException {
        return destinationDAO.save(new Destination(destinationDTO.getDestinationId(), destinationDTO.getDestinationName(), destinationDTO.getDistance()));
    }

    @Override
    // Method to find a destination by ID
    public DestinationDTO findById(String selectedDestinationId) throws SQLException {
        Destination destination = destinationDAO.findById(selectedDestinationId);
        return new DestinationDTO(destination.getDestinationId(), destination.getDestinationName(), destination.getDistance());
    }

    @Override
    public DestinationDTO findByName(String selectedDestinationName) throws SQLException {
        Destination destination = destinationDAO.findByName(selectedDestinationName);
        return new DestinationDTO(destination.getDestinationId(), destination.getDestinationName(), destination.getDistance());
    }

}
