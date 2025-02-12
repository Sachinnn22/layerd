package lk.ijse.gdse.AirTicket.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/PlaneView.fxml");
    }

    @FXML
    void navigateToPlanePage(ActionEvent event) {
        navigateTo("/view/PlaneView.fxml");
    }

    @FXML
    void navigateToDestinationPage(ActionEvent event) {
        navigateTo("/view/DestinationView.fxml");
    }

    @FXML
    void navigateToTicketPage(ActionEvent event) {
        navigateTo("/view/TicketView.fxml");
    }

    @FXML
    void navigateToSeatPage(ActionEvent event) {
        navigateTo("/view/SeatView.fxml");
    }

    @FXML
    void navigateToLoginPage(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            anchor.getChildren().clear();
            anchor.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Login.fxml")));
        }
    }

    @FXML
    void navigateToPassengerPage(ActionEvent event) {
        navigateTo("/view/PassenegrView.fxml");
    }

    @FXML
    void navigateToBookingPage(ActionEvent event) {
        navigateTo("/view/BookingView.fxml");
    }

    void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            content.getChildren().add(load); // Add the loaded view to the content pane
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load the view: " + fxmlPath).show();
        }
    }



}
