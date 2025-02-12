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
import lk.ijse.gdse.AirTicket.bo.custom.impl.PlaneBOImpl;
import lk.ijse.gdse.AirTicket.dto.PlaneDto;
import lk.ijse.gdse.AirTicket.dto.tm.PlaneTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class  PlaneController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PlaneTm, String> colPlaneId;
    @FXML
    private TableColumn<PlaneTm, String> colPlaneName;
    @FXML
    private TableColumn<PlaneTm, String> colFlightClass;
    @FXML
    private TableColumn<PlaneTm, String> colSeatCount;

    @FXML
    private Label lblPlaneId;

    @FXML
    private TableView<PlaneTm> tblPlane;

    @FXML
    private TextField txtPlaneName;

    @FXML
    private TextField txtClass;

    @FXML
    private TextField txtSeatCount;

    PlaneBO planeBO = (PlaneBO) FactoryBO.getInstance().getBo(FactoryBO.BoType.PLANE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colPlaneId.setCellValueFactory(new PropertyValueFactory<>("planeId"));
        colPlaneName.setCellValueFactory(new PropertyValueFactory<>("planeName"));
        colFlightClass.setCellValueFactory(new PropertyValueFactory<>("flightClass"));
        colSeatCount.setCellValueFactory(new PropertyValueFactory<>("seatCount"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load plane ID").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextPlaneId();
        loadTableData();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtPlaneName.clear();
        txtClass.clear();
        txtSeatCount.clear();
    }

    private void loadTableData() throws SQLException {
        ArrayList<PlaneDto> planeDtos = planeBO.getAll();
        ObservableList<PlaneTm> planeTms = FXCollections.observableArrayList();

        for (PlaneDto planeDto : planeDtos) {
            PlaneTm planeTm = new PlaneTm(
                    planeDto.getPlaneId(),
                    planeDto.getPlaneName(),
                    planeDto.getFlightClass(),
                    planeDto.getSeatCount()
            );
            planeTms.add(planeTm);
        }

        tblPlane.setItems(planeTms);
    }

    public void loadNextPlaneId() throws SQLException {
        String nextPlaneId = planeBO.getNextId();
        lblPlaneId.setText(nextPlaneId);
    }

    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String planeId = lblPlaneId.getText();
        String planeName = txtPlaneName.getText();
        String flightClass = txtClass.getText();
        String seatCount = txtSeatCount.getText();

        PlaneDto planeDto = new PlaneDto(
                planeId,
                planeName,
                flightClass,
                seatCount
        );

        boolean isSaved = planeBO.save(planeDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Plane saved!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save plane!").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        PlaneTm planeTm = tblPlane.getSelectionModel().getSelectedItem();
        if (planeTm != null) {
            lblPlaneId.setText(planeTm.getPlaneId());
            txtPlaneName.setText(planeTm.getPlaneName());
            txtClass.setText(planeTm.getFlightClass());
            txtSeatCount.setText(planeTm.getSeatCount());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String planeId = lblPlaneId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = planeBO.delete(planeId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Plane deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete plane!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String planeId = lblPlaneId.getText();
        String planeName = txtPlaneName.getText();
        String flightClass = txtClass.getText();
        String seatCount = txtSeatCount.getText();

        PlaneDto planeDto = new PlaneDto(
                planeId,
                planeName,
                flightClass,
                seatCount
        );

        boolean isUpdated = planeBO.update(planeDto);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Plane updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update plane!").show();
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }
}
