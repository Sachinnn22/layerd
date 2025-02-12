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
import lk.ijse.gdse.AirTicket.bo.custom.DestinationBO;
import lk.ijse.gdse.AirTicket.bo.custom.PlaneBO;
import lk.ijse.gdse.AirTicket.bo.custom.impl.DestinationBOImpl;
import lk.ijse.gdse.AirTicket.dao.custom.DestinationDAO;
import lk.ijse.gdse.AirTicket.dto.DestinationDTO;
import lk.ijse.gdse.AirTicket.dto.tm.DestinationTm;
import lk.ijse.gdse.AirTicket.dao.custom.impl.DestinationDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DestinationController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<DestinationTm,String> colDesName;

    @FXML
    private TableColumn<DestinationTm,String> colDestinationId;

    @FXML
    private TableColumn<DestinationTm,String> colDistance;


    @FXML
    private Label lblDestinationId;

    @FXML
    private TableView<DestinationTm> tblDestination;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQuantity;

    DestinationBO destinationBO = (DestinationBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.DESTINATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set cell value factories for the table columns
        colDestinationId.setCellValueFactory(new PropertyValueFactory<>("destinationId"));
        colDesName.setCellValueFactory(new PropertyValueFactory<>("destinationName"));
        colDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));

        // Load data and setup UI state
        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load destinations").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextDestinationId();
        loadTableData();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.clear();
        txtQuantity.clear();
    }

    private void loadTableData() throws SQLException {
        ArrayList<DestinationDTO> destinationDTOS = destinationBO.getAll();
        ObservableList<DestinationTm> destinationList = FXCollections.observableArrayList();

        for (DestinationDTO destinationDTO : destinationDTOS) {
            DestinationTm destinationTm = new DestinationTm();
            destinationTm.setDestinationId(destinationDTO.getDestinationId());
            destinationTm.setDestinationName(destinationDTO.getDestinationName());
            destinationTm.setDistance(destinationDTO.getDistance());
            destinationList.add(destinationTm);
        }

        tblDestination.setItems(destinationList);
    }

    private void loadNextDestinationId() throws SQLException {
        String nextDestinationId = destinationBO.getNextId();
        lblDestinationId.setText(nextDestinationId);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String destinationId = lblDestinationId.getText();
        String name = txtName.getText();
        String distance = txtQuantity.getText();

        DestinationDTO destinationDTO = new DestinationDTO(destinationId, name, distance);
        boolean isSaved = destinationBO.save(destinationDTO);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Destination saved!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save destination!").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        DestinationTm selectedDestination = tblDestination.getSelectionModel().getSelectedItem();
        if (selectedDestination != null) {
            lblDestinationId.setText(selectedDestination.getDestinationId());
            txtName.setText(selectedDestination.getDestinationName());
            txtQuantity.setText(selectedDestination.getDistance());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String destinationId = lblDestinationId.getText();
        String name = txtName.getText();
        String distance = txtQuantity.getText();

        DestinationDTO destinationDTO = new DestinationDTO(destinationId, name, distance);
        boolean isUpdated = destinationBO.update(destinationDTO);

        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Destination updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update destination!").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String destinationId = lblDestinationId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = destinationBO.delete(destinationId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Destination deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete destination!").show();
            }
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }
}
