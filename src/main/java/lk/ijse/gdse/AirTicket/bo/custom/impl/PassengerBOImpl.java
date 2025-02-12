package lk.ijse.gdse.AirTicket.bo.custom.impl;

import lk.ijse.gdse.AirTicket.bo.custom.PassangerBO;
import lk.ijse.gdse.AirTicket.dao.custom.PassengerDAO;
import lk.ijse.gdse.AirTicket.dao.custom.impl.PassengerDAOImpl;
import lk.ijse.gdse.AirTicket.dto.PassengerDto;
import lk.ijse.gdse.AirTicket.dto.PlaneDto;
import lk.ijse.gdse.AirTicket.entity.Passenger;

import java.sql.SQLException;
import java.util.ArrayList;

public class PassengerBOImpl implements PassangerBO {

    PassengerDAO passengerDAO = new PassengerDAOImpl();

    public boolean save(PassengerDto passengerDto) throws SQLException {
        return passengerDAO.save(new Passenger(passengerDto.getName(), passengerDto.getAge(), passengerDto.getNic(),passengerDto.getMobileNumber()));

    }

    @Override
    public String getNextId() throws SQLException {
        return passengerDAO.getNextId();
    }


    @Override
    public ArrayList<PassengerDto> getAll() throws SQLException {
        ArrayList<PassengerDto> passengerDtos = new ArrayList<>();
        ArrayList<Passenger> passengers = passengerDAO.getAll();
        for (Passenger passenger : passengers) {
            PassengerDto passengerDto = new PassengerDto(
                    passenger.getName(),
                    passenger.getAge(),
                    passenger.getNic(),
                    passenger.getMobileNumber()
            );
            passengerDtos.add(passengerDto);
        }
        return passengerDtos;
    }

    @Override
    public boolean update(PassengerDto passengerDto) throws SQLException {
        return passengerDAO.update(new Passenger(passengerDto.getName(), passengerDto.getAge(), passengerDto.getNic(),passengerDto.getMobileNumber()));
    }

    @Override
    public boolean delete(String planeId) throws SQLException {
        return passengerDAO.delete(planeId);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return passengerDAO.getAllIds();
    }

    @Override
    public PassengerDto findById(String selectedPlaneId) throws SQLException {
        Passenger passenger = passengerDAO.findById(selectedPlaneId);
        return new PassengerDto(passenger.getName(), passenger.getAge(), passenger.getNic(),passenger.getMobileNumber());
    }

    @Override
    public PassengerDto findByName(String selectedId) throws SQLException {
        return null;
    }
}