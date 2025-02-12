package lk.ijse.gdse.AirTicket.bo;

import lk.ijse.gdse.AirTicket.bo.custom.impl.*;
import lk.ijse.gdse.AirTicket.dao.FactoryDAO;
import lk.ijse.gdse.AirTicket.dao.SuperDAO;
import lk.ijse.gdse.AirTicket.dao.custom.impl.DestinationDAOImpl;
import lk.ijse.gdse.AirTicket.dao.custom.impl.PlaneDAOImpl;
import lk.ijse.gdse.AirTicket.dao.custom.impl.TicketDAOImpl;

public class FactoryBO {
    private static FactoryBO factoryBO;

    private FactoryBO(){}

    public static FactoryBO getInstance() {
        if(factoryBO == null){
            factoryBO = new FactoryBO();

        }
        return factoryBO;
    }
    public enum BoType{

        PLANE,DESTINATION,TICKET,SEAT,PASSENGER,BOOKING

    }

    public SuperBO getBo(FactoryBO.BoType boType){
        switch (boType){
            case PLANE:
                return new PlaneBOImpl();
            case DESTINATION:
                return new DestinationBOImpl();
            case TICKET:
                return new TicketBOImpl();
            case SEAT:
                return new SeatBOImpl();
            case PASSENGER:
                return new PassengerBOImpl();
            case BOOKING:
                return new BookingBOImpl();
            default:
                return null;
        }
    }

}
