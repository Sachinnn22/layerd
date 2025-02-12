package lk.ijse.gdse.AirTicket.dao.custom.impl;

import lk.ijse.gdse.AirTicket.dao.custom.TicketDAO;
import lk.ijse.gdse.AirTicket.dto.TicketDto;
import lk.ijse.gdse.AirTicket.dao.CrudUtil;
import lk.ijse.gdse.AirTicket.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDAOImpl implements TicketDAO {

    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT ticket_id FROM ticket ORDER BY ticket_id DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("T%03d", newIdIndex);
        }
        return "T001";
    }

    @Override
    public boolean save(Ticket ticket) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO ticket VALUES (?, ?, ?, ?, ?)",
                ticket.getTicketId(),
                ticket.getDestinationId(),
                ticket.getPlaneId(),
                ticket.getTicketClass(),
                ticket.getTicketCost()
        );
    }

    public ArrayList<Ticket> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM ticket");

        ArrayList<Ticket> tickets = new ArrayList<>();

        while (rst.next()) {
            Ticket ticket = new Ticket(
                    rst.getString(1),  // ticket_id
                    rst.getString(2),  // destination_id
                    rst.getString(3),  // plane_id
                    rst.getString(4),  // seat_class
                    rst.getDouble(5)   // ticket_cost
            );
            tickets.add(ticket);
        }
        return tickets;
    }

    public boolean update(Ticket ticket) throws SQLException {
        return CrudUtil.execute(
                "UPDATE ticket SET destination_id = ?, plane_id = ?, seat_class = ?, ticket_class = ?, ticket_cost = ? WHERE ticket_id = ?",
                ticket.getDestinationId(),
                ticket.getPlaneId(),
                ticket.getTicketClass(),
                ticket.getTicketCost(),
                ticket.getTicketId()
        );
    }

    public boolean delete(String ticketId) throws SQLException {
        return CrudUtil.execute("DELETE FROM ticket WHERE ticket_id = ?", ticketId);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT ticket_id FROM ticket");

        ArrayList<String> ticketIds = new ArrayList<>();

        while (rst.next()) {
            ticketIds.add(rst.getString(1));
        }
        return ticketIds;
    }

    public Ticket findById(String selectedTicketId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM ticket WHERE ticket_id = ?", selectedTicketId);

        if (rst.next()) {
            return new Ticket(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public Ticket findByName(String selectedId) throws SQLException {
        return null;
    }
}
