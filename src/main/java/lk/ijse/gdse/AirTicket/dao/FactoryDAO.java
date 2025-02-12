package lk.ijse.gdse.AirTicket.dao;

import lk.ijse.gdse.AirTicket.dao.custom.impl.*;

public class FactoryDAO {
    private static FactoryDAO factoryDAO;

    private FactoryDAO(){}

    public static FactoryDAO getInstance() {
        if(factoryDAO == null){
            factoryDAO = new FactoryDAO();

        }
        return factoryDAO;
    }
    public enum DaoType{

        PLANE,DESTINATION,TICKET,SEAT,PASSENGER,BOOKING

    }

    public SuperDAO getDao(DaoType daoType){
        switch (daoType){
            case PLANE:
                return new PlaneDAOImpl();
            case DESTINATION:
                return new DestinationDAOImpl();
            case TICKET:
                return new TicketDAOImpl();
            case SEAT:
                return new SeatDAOImpl();
            case PASSENGER:
                return new PassengerDAOImpl();
            case BOOKING:
                return new BookingDAOImpl();
            default:
                return null;
        }
    }

}
