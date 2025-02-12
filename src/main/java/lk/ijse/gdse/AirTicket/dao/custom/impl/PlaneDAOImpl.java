package lk.ijse.gdse.AirTicket.dao.custom.impl;

import lk.ijse.gdse.AirTicket.dao.custom.PlaneDAO;
import lk.ijse.gdse.AirTicket.dto.PlaneDto;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.entity.Plane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaneDAOImpl implements PlaneDAO {

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT plane_id FROM plane ORDER BY plane_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }

    @Override
    public boolean save(Plane plane) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO plane VALUES (?, ?, ?, ?)",
                plane.getPlaneId(),
                plane.getPlaneName(),
                plane.getFlightClass(),
                Integer.parseInt(plane.getSeatCount())
        );
    }

    @Override
    public ArrayList<Plane> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM plane");

        ArrayList<Plane> planes = new ArrayList<>();

        while (rst.next()) {
            Plane plane = new Plane(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    String.valueOf(rst.getInt(4))
            );
            planes.add(plane);
        }
        return planes;
    }

    @Override
    public boolean update(Plane plane) throws SQLException {
        return CrudUtil.execute(
                "UPDATE plane SET plane_name = ?, flight_class = ?, seat_count = ? WHERE plane_id = ?",
                plane.getPlaneName(),
                plane.getFlightClass(),
                Integer.parseInt(plane.getSeatCount()),
                plane.getPlaneId()
        );
    }

    @Override
    public boolean delete(String planeId) throws SQLException {
        return CrudUtil.execute("DELETE FROM plane WHERE plane_id = ?", planeId);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT plane_id FROM plane");

        ArrayList<String> planeIds = new ArrayList<>();

        while (rst.next()) {
            planeIds.add(rst.getString(1));
        }
        return planeIds;
    }

    @Override
    public Plane findById(String selectedPlaneId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM plane WHERE plane_id = ?", selectedPlaneId);

        if (rst.next()) {
            return new Plane(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    String.valueOf(rst.getInt(4))
            );
        }
        return null;
    }

    @Override
    public Plane findByName(String name) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM plane WHERE plane_name = ?", name);

        if (rst.next()) {
            return new Plane(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    String.valueOf(rst.getInt(4))
            );
        }
        return null;
    }
}

