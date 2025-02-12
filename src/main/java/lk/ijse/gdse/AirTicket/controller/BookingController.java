package lk.ijse.gdse.AirTicket.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.gdse.AirTicket.bo.FactoryBO;
import lk.ijse.gdse.AirTicket.bo.custom.*;
import lk.ijse.gdse.AirTicket.dto.*;
import lk.ijse.gdse.AirTicket.dto.tm.BookingTm;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookingController implements Initializable {

    @FXML
    private Label BookingIdLbl;

    @FXML
    private Label balanceLbl;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<String, BookingTm> colBookingId;

    @FXML
    private TableColumn<String, BookingTm> colDestination;

    @FXML
    private TableColumn<String, BookingTm> colPlaneId;

    @FXML
    private TableColumn<String, BookingTm> colSeatId;

    @FXML
    private TableColumn<String, BookingTm> colTicketId;

    @FXML
    private ComboBox<String> comboPassengerId;

    @FXML
    private ComboBox<String> comboSeatId;

    @FXML
    private ComboBox<String> comboTicketId;

    @FXML
    private Label dateLbl;

    @FXML
    private Label planeLbl;

    @FXML
    private Label destinationLbl;

    @FXML
    private Label ticketPriceLbl;

    @FXML
    private TableView<BookingTm> tblBooking;

    @FXML
    private TextField txtGivingPrice;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPaymentConductor;

    @FXML
    private TextField txtPaymentMethod;

    BookingBO bookingBO = (BookingBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.BOOKING);
    TicketBO ticketBO = (TicketBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.TICKET);
    DestinationBO destinationBO = (DestinationBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.DESTINATION);
    SeatBO seatBO = (SeatBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.SEAT);
    PlaneBO planeBO = (PlaneBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.PLANE);
    PassangerBO passangerBO = (PassangerBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.PASSENGER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colTicketId.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        colPlaneId.setCellValueFactory(new PropertyValueFactory<>("planeId"));
        colSeatId.setCellValueFactory(new PropertyValueFactory<>("seatId"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destinationId"));

        try {
            refreshPage();
            loadComboBoxes();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextBookingId();
        loadTableData();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);

        comboPassengerId.getSelectionModel().clearSelection();
        comboTicketId.getSelectionModel().clearSelection();
        comboSeatId.getSelectionModel().clearSelection();
        balanceLbl.setText("");
        txtGivingPrice.setText("");
        txtMobileNumber.setText("");
        txtNic.setText("");
        dateLbl.setText(LocalDate.now().toString());
        txtPaymentConductor.setText("");
        destinationLbl.setText("");
        planeLbl.setText("");
        txtPaymentMethod.setText("");
//        txtTicketPrice.setText("");

    }

    public void loadNextBookingId() throws SQLException {
        String nextSeatId = bookingBO.getNextId();
        BookingIdLbl.setText(nextSeatId);
    }

    private void loadTableData() throws SQLException {
        ArrayList<BookingDto> bookingDtos = bookingBO.getAll();
        ObservableList<BookingTm> bookingTms = FXCollections.observableArrayList();

        for (BookingDto bookingDto : bookingDtos) {
            BookingTm bookingTm = new BookingTm(
                    bookingDto.getBookingId(),
                    bookingDto.getSeatId(),
                    bookingDto.getPlaneId(),
                    bookingDto.getDestinationId(),
                    bookingDto.getTicketId()
            );
            bookingTms.add(bookingTm);
        }

        tblBooking.setItems(bookingTms);
    }

    private void loadComboBoxes() throws SQLException {
        ArrayList<String> seatIds = seatBO.getAllIds();
        comboSeatId.getItems().addAll(seatIds);

        ArrayList<String> ticketIds = ticketBO.getAllIds();
        comboTicketId.getItems().addAll(ticketIds);

        ArrayList<String> passengerIds = passangerBO.getAllIds();
        comboPassengerId.getItems().addAll(passengerIds);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String bookingId = BookingIdLbl.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = bookingBO.delete(bookingId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Booking deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Booking!").show();
            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String bookingId = BookingIdLbl.getText();
        String nic = txtNic.getText();
        String mobileNumber = txtMobileNumber.getText();
        String ticketId = comboTicketId.getValue();
        String planeId = planeBO.findByName(planeLbl.getText()).getPlaneId();
        String seatId = comboSeatId.getValue();
        String destinationId = destinationBO.findByName(destinationLbl.getText()).getDestinationId();
        double ticketPrice = Double.parseDouble(ticketPriceLbl.getText());
        Date date = Date.valueOf(LocalDate.now());
        String paymentMethod =txtPaymentMethod.getText();
        double balance = Double.parseDouble(balanceLbl.getText());
        double givingPrice = Double.parseDouble(txtGivingPrice.getText());
        String userName = txtPaymentConductor.getText();

        BookingDto bookingDto = new BookingDto(
                bookingId,
                nic,
                mobileNumber,
                ticketId,
                planeId,
                seatId,
                destinationId,
                ticketPrice,
                date,
                paymentMethod,
                balance,
                givingPrice,
                userName
        );

        boolean isSaved = bookingBO.save(bookingDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Booking saved!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save booking!").show();
        }
    }

    @FXML
    void comboSeatIdOnAction(ActionEvent event) throws SQLException {
        if (comboSeatId.getValue() == null) return;

        SeatDto seatDto = seatBO.findById(comboSeatId.getValue());

        if (seatDto != null) {
            PlaneDto planeDto = planeBO.findById(seatDto.getPlaneId());
            if (planeDto != null) {
                planeLbl.setText(planeDto.getPlaneName());
            }
        }
    }

    @FXML
    void comboTicketIdOnAction(ActionEvent event) throws SQLException {
        if (comboTicketId.getValue() == null) return;

        TicketDto ticketDto = ticketBO.findById(comboTicketId.getValue());

        if (ticketDto != null) {
            DestinationDTO destinationDTO = destinationBO.findById(ticketDto.getDestinationId());
            if (destinationDTO != null) {
                destinationLbl.setText(destinationDTO.getDestinationName());
            }
            ticketPriceLbl.setText(ticketDto.getTicketCost().toString());
        }
    }


    @FXML
    void priceOnKeyTyped(KeyEvent event) {
        if (txtGivingPrice.getText().isEmpty()) {
            return;
        } else if (!txtGivingPrice.getText().matches("\\d+")) {
            balanceLbl.setText("Invalid");
            return;
        }

        double ticketPrice = Double.parseDouble(ticketPriceLbl.getText());
        double givingPrice = Double.parseDouble(txtGivingPrice.getText());

        if (ticketPrice <= givingPrice) {
            double balance = givingPrice - ticketPrice;
            balanceLbl.setText(String.format("%.2f", balance));
        } else if (ticketPrice > givingPrice) {
            balanceLbl.setText("Insufficient funds");
        }
    }
}
