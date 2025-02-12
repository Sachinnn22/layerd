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
import lk.ijse.gdse.AirTicket.bo.custom.PassangerBO;
import lk.ijse.gdse.AirTicket.dto.PassengerDto;
import lk.ijse.gdse.AirTicket.dto.PlaneDto;
import lk.ijse.gdse.AirTicket.dto.tm.PassengerTm;
import lk.ijse.gdse.AirTicket.dto.tm.PlaneTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PassenegerController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PassengerTm, String> colAge;

    @FXML
    private TableColumn<PassengerTm, String> colMobile;

    @FXML
    private TableColumn<PassengerTm, String> colName;

    @FXML
    private TableColumn<PassengerTm, String> colNic;

    @FXML
    private TableView<PassengerTm> tblPassenger;

    @FXML
    private TextField txtPassengerAge;

    @FXML
    private TextField txtPassengerMobile;

    @FXML
    private TextField txtPassengerName;

    @FXML
    private TextField txtPassengerNic;

    PassangerBO passangerBO = (PassangerBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.PASSENGER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Passenger ID").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadTableData();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtPassengerAge.clear();
        txtPassengerName.clear();
        txtPassengerMobile.clear();
        txtPassengerNic.clear();
    }

    private void loadTableData() throws SQLException {
        ArrayList<PassengerDto> passengerDtos = passangerBO.getAll();
        ObservableList<PassengerTm> passengerTms = FXCollections.observableArrayList();

        for (PassengerDto passengerDto : passengerDtos) {
            PassengerTm passengerTm = new PassengerTm(
                    passengerDto.getName(),
                    passengerDto.getAge(),
                    passengerDto.getNic(),
                    passengerDto.getMobileNumber()
            );
            passengerTms.add(passengerTm);
        }

        tblPassenger.setItems(passengerTms);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String passenger = txtPassengerName.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = passangerBO.delete(passenger);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Passenger deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Passenger!").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String name = txtPassengerName.getText();
        String age = txtPassengerAge.getText();
        String nic = txtPassengerNic.getText();
        String mobile = txtPassengerMobile.getText();

        PassengerDto passengerDto = new PassengerDto(
                name,
                age,
                nic,
                mobile
        );

        boolean isSaved = passangerBO.save(passengerDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Passenger saved!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save Passenger!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String name = txtPassengerName.getText();
        String age = txtPassengerAge.getText();
        String nic = txtPassengerNic.getText();
        String mobile = txtPassengerMobile.getText();

        PassengerDto passengerDto = new PassengerDto(
                name,
                age,
                nic,
                mobile
        );

        boolean isUpdated = passangerBO.update(passengerDto);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Passenger updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update Passenger!").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        PassengerTm passengerTm = tblPassenger.getSelectionModel().getSelectedItem();
        if (passengerTm != null) {
            txtPassengerName.setText(passengerTm.getName());
            txtPassengerAge.setText(passengerTm.getAge());
            txtPassengerNic.setText(passengerTm.getNic());
            txtPassengerMobile.setText(passengerTm.getMobileNumber());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }
}
