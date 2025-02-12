package lk.ijse.gdse.AirTicket.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.AirTicket.bo.FactoryBO;
import lk.ijse.gdse.AirTicket.bo.custom.PlaneBO;
import lk.ijse.gdse.AirTicket.bo.custom.TicketBO;
import lk.ijse.gdse.AirTicket.bo.custom.impl.TicketBOImpl;
import lk.ijse.gdse.AirTicket.dto.TicketDto;
import lk.ijse.gdse.AirTicket.dto.tm.TicketTm;
import lk.ijse.gdse.AirTicket.dao.custom.impl.DestinationDAOImpl;
import lk.ijse.gdse.AirTicket.dao.custom.impl.PlaneDAOImpl;
import lk.ijse.gdse.AirTicket.dao.custom.impl.TicketDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TicketController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> comboClass;

    @FXML
    private ComboBox<String> comboDestinationId;

    @FXML
    private ComboBox<String> comboPlaneId;

    @FXML
    private TableColumn<TicketTm, String> colDestinationId;

    @FXML
    private TableColumn<TicketTm, String> colPlaneId;

    @FXML
    private TableColumn<TicketTm, String> colTicketClass;

    @FXML
    private TableColumn<TicketTm, Double> colTicketCost;

    @FXML
    private TableColumn<TicketTm, String> colTicketId;

    @FXML
    private Label lblTicketId;

    @FXML
    private TableView<TicketTm> tblTickets;

    @FXML
    private TextField txtTicketCost;

    TicketBO ticketBO = (TicketBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.TICKET);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTicketId.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        colDestinationId.setCellValueFactory(new PropertyValueFactory<>("destinationId"));
        colPlaneId.setCellValueFactory(new PropertyValueFactory<>("planeId"));
        colTicketClass.setCellValueFactory(new PropertyValueFactory<>("ticketClass"));
        colTicketCost.setCellValueFactory(new PropertyValueFactory<>("ticketCost"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load data").show();
        }
    }

    @FXML
    private void refreshPage() throws SQLException {
        loadNextTicketId();
        loadTableData();
        loadComboBoxes();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        comboDestinationId.getSelectionModel().clearSelection();
        comboPlaneId.getSelectionModel().clearSelection();
        comboClass.getSelectionModel().clearSelection();
        txtTicketCost.clear();
    }

    private void loadComboBoxes() throws SQLException {
        ArrayList<String> destinationIds = new DestinationDAOImpl().getAllIds();
        ObservableList<String> observableList1 = FXCollections.observableArrayList();
        observableList1.addAll(destinationIds);
        comboDestinationId.setItems(observableList1);

        ArrayList<String> planeIds = new PlaneDAOImpl().getAllIds();
        ObservableList<String> observableList2 = FXCollections.observableArrayList();
        observableList2.addAll(planeIds);
        comboPlaneId.setItems(observableList2);

        String[] type={"Business", "First Class", "Second"};
        comboClass.getItems().addAll(type);
    }

    private void loadTableData() throws SQLException {
        ArrayList<TicketDto> tickets = ticketBO.getAll();
        ObservableList<TicketTm> ticketTms = FXCollections.observableArrayList();

        for (TicketDto ticket : tickets) {
            TicketTm ticketTM = new TicketTm(
                    ticket.getTicketId(),
                    ticket.getDestinationId(),
                    ticket.getPlaneId(),
                    ticket.getTicketClass(),
                    ticket.getTicketCost()
            );
            ticketTms.add(ticketTM);
        }

        tblTickets.setItems(ticketTms);
    }

    private void loadNextTicketId() throws SQLException {
        String nextTicketId = ticketBO.getNextId();
        lblTicketId.setText(nextTicketId);
    }

    @FXML
    void btnSaveOnAcction(ActionEvent event) throws SQLException {
        TicketDto ticket = new TicketDto(
                lblTicketId.getText(),
                comboDestinationId.getValue(),
                comboPlaneId.getValue(),
                comboClass.getValue(),
                Double.parseDouble(txtTicketCost.getText())
        );

        boolean isSaved = ticketBO.save(ticket);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Ticket saved!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save ticket!").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAcction(ActionEvent event) throws SQLException {
        TicketDto ticket = new TicketDto(
                lblTicketId.getText(),
                comboDestinationId.getValue(),
                comboPlaneId.getValue(),
                comboClass.getValue(),
                Double.parseDouble(txtTicketCost.getText())
        );

        boolean isUpdated = ticketBO.update(ticket);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Ticket updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update ticket!").show();
        }
    }

    @FXML
    void btnDeleteOnAcction(ActionEvent event) throws SQLException {
        String ticketId = lblTicketId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = ticketBO.delete(ticketId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Ticket deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete ticket!").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        TicketTm selectedTicket = tblTickets.getSelectionModel().getSelectedItem();
        if (selectedTicket != null) {
            lblTicketId.setText(selectedTicket.getTicketId());
            comboDestinationId.setValue(selectedTicket.getDestinationId());
            comboPlaneId.setValue(selectedTicket.getPlaneId());
            comboClass.setValue(selectedTicket.getTicketClass());
            txtTicketCost.setText(String.valueOf(selectedTicket.getTicketCost()));

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
