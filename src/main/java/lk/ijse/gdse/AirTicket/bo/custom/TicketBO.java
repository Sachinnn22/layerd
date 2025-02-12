package lk.ijse.gdse.AirTicket.bo.custom;

import lk.ijse.gdse.AirTicket.bo.SuperBO;
import lk.ijse.gdse.AirTicket.dto.TicketDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TicketBO extends SuperBO {


    public String getNextId() throws SQLException ;

    public boolean save(TicketDto ticketDto) throws SQLException ;

    public ArrayList<TicketDto> getAll() throws SQLException ;

    public boolean update(TicketDto ticketDto) throws SQLException ;

    public boolean delete(String ticketId) throws SQLException ;

    public ArrayList<String> getAllIds() throws SQLException ;

    public TicketDto findById(String selectedTicketId) throws SQLException ;
}
