package lk.ijse.gdse.AirTicket.bo.custom.impl;

import lk.ijse.gdse.AirTicket.bo.custom.TicketBO;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.dao.custom.TicketDAO;
import lk.ijse.gdse.AirTicket.dao.custom.impl.TicketDAOImpl;
import lk.ijse.gdse.AirTicket.dto.TicketDto;
import lk.ijse.gdse.AirTicket.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketBOImpl implements TicketBO {
    TicketDAO ticketDAO = new TicketDAOImpl();

    public String getNextId() throws SQLException {
        return ticketDAO.getNextId();
    }

    public boolean save(TicketDto ticketDto) throws SQLException {
        return ticketDAO.save(new Ticket(ticketDto.getTicketId(), ticketDto.getDestinationId(), ticketDto.getPlaneId(), ticketDto.getTicketClass(), ticketDto.getTicketCost()));
    }

    public ArrayList<TicketDto> getAll() throws SQLException {
        ArrayList<TicketDto> ticketDtos = new ArrayList<>();
        ArrayList<Ticket> tickets = ticketDAO.getAll();

        for (Ticket ticket : tickets) {
            TicketDto ticketDto = new TicketDto(
                    ticket.getTicketId(),
                    ticket.getDestinationId(),
                    ticket.getPlaneId(),
                    ticket.getTicketClass(),
                    ticket.getTicketCost()
            );
            ticketDtos.add(ticketDto);
        }
        return ticketDtos;
    }

    public boolean update(TicketDto ticketDto) throws SQLException {
        return ticketDAO.update(new Ticket(ticketDto.getTicketId(), ticketDto.getDestinationId(), ticketDto.getPlaneId(), ticketDto.getTicketClass(), ticketDto.getTicketCost()));
    }

    public boolean delete(String ticketId) throws SQLException {
        return ticketDAO.delete(ticketId);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        return ticketDAO.getAllIds();
    }

    public TicketDto findById(String selectedTicketId) throws SQLException {
        Ticket ticket = ticketDAO.findById(selectedTicketId);
        return new TicketDto(ticket.getTicketId(), ticket.getDestinationId(), ticket.getPlaneId(), ticket.getTicketClass(), ticket.getTicketCost());
    }

}