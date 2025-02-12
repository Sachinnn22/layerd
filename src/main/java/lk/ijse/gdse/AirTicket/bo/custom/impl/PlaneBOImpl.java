package lk.ijse.gdse.AirTicket.bo.custom.impl;

import lk.ijse.gdse.AirTicket.bo.custom.PlaneBO;
import lk.ijse.gdse.AirTicket.dao.FactoryDAO;
import lk.ijse.gdse.AirTicket.dao.custom.PlaneDAO;
import lk.ijse.gdse.AirTicket.dao.custom.impl.PlaneDAOImpl;
import lk.ijse.gdse.AirTicket.dto.PlaneDto;
import lk.ijse.gdse.AirTicket.entity.Plane;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaneBOImpl implements PlaneBO {

    PlaneDAO planeDAO = (PlaneDAO) FactoryDAO.getInstance().getDao(FactoryDAO.DaoType.PLANE);

    public String getNextId() throws SQLException {
        return planeDAO.getNextId();
    }


    public boolean save(PlaneDto planeDto) throws SQLException {
        return planeDAO.save(new Plane(planeDto.getPlaneId(), planeDto.getPlaneName(), planeDto.getFlightClass(), planeDto.getSeatCount()));
    }

    public ArrayList<PlaneDto> getAll() throws SQLException {
        ArrayList<PlaneDto> planeDtos = new ArrayList<>();
        ArrayList<Plane> planes = planeDAO.getAll();
        for (Plane plane : planes) {
            PlaneDto planeDto = new PlaneDto(
                    plane.getPlaneId(),
                    plane.getPlaneName(),
                    plane.getFlightClass(),
                    plane.getSeatCount()
            );
            planeDtos.add(planeDto);
        }
        return planeDtos;
    }


    public boolean update(PlaneDto planeDto) throws SQLException {
        return planeDAO.update(new Plane(planeDto.getPlaneId(), planeDto.getPlaneName(), planeDto.getFlightClass(), planeDto.getSeatCount()));
    }


    public boolean delete(String planeId) throws SQLException {
        return planeDAO.delete(planeId);
    }


    public ArrayList<String> getAllIds() throws SQLException {
        return planeDAO.getAllIds();
    }


    public PlaneDto findById(String selectedPlaneId) throws SQLException {
        Plane plane = planeDAO.findById(selectedPlaneId);
        return new PlaneDto(plane.getPlaneId(), plane.getPlaneName(), plane.getFlightClass(), plane.getSeatCount());
    }

    public PlaneDto findByName(String name) throws SQLException {
        Plane plane = planeDAO.findByName(name);
        return new PlaneDto(plane.getPlaneId(), plane.getPlaneName(), plane.getFlightClass(), plane.getSeatCount());
    }
}