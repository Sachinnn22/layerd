package lk.ijse.gdse.AirTicket.dao.custom.impl;

import lk.ijse.gdse.AirTicket.dao.custom.PassengerDAO;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.entity.Passenger;
import lk.ijse.gdse.AirTicket.entity.Plane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengerDAOImpl implements PassengerDAO {


    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    public boolean save(Passenger entity) throws SQLException {

        return CrudUtil.execute(
                "insert into passenger values (?,?,?,?)",
                entity.getName(),
                entity.getAge(),
                entity.getNic(),
                entity.getMobileNumber()
        );

    }

    @Override
    public ArrayList<Passenger> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM passenger");

        ArrayList<Passenger> passengers = new ArrayList<>();

        while (rst.next()) {
            Passenger passenger = new Passenger(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    String.valueOf(rst.getInt(4))
            );
            passengers.add(passenger);
        }
        return passengers;
    }

    @Override
    public boolean update(Passenger entity) throws SQLException {
        return CrudUtil.execute(
                "UPDATE plane SET passenger_age = ?, nic = ?, mobile_number = ? WHERE passenger_name = ?",
                Integer.parseInt(entity.getAge()),
                Integer.parseInt(entity.getNic()),
                Integer.parseInt(entity.getMobileNumber()),
                entity.getName()
        );
    }

    @Override
    public boolean delete(String planeId) throws SQLException {
        return CrudUtil.execute("DELETE FROM passenger WHERE passenger_name = ?", planeId);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT passenger_name FROM passenger");

        ArrayList<String> passengernames = new ArrayList<>();

        while (rst.next()) {
            passengernames.add(rst.getString(1));
        }
        return passengernames;
    }

    @Override
    public Passenger findById(String selectedName) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM passenger WHERE passenger_name = ?", selectedName);

        if (rst.next()) {
            return new Passenger(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public Passenger findByName(String selectedId) throws SQLException {
        return null;
    }

}
