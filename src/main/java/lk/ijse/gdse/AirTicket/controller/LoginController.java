package lk.ijse.gdse.AirTicket.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane Loginanc1;

    @FXML
    private AnchorPane Loginanc2;

    @FXML
    private Label lblCheckPassword;

    @FXML
    private Label lblCheckUsername;

    @FXML
    private AnchorPane loginPage;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtName;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = txtName.getText();
        String password = txtPassword.getText();

        txtName.setStyle("-fx-border-color: #7367F0;");
        txtPassword.setStyle("-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String passwordPattern = "^[A-Za-z0-9]+$";

        boolean isValidName = username.matches(namePattern);
        boolean isValidPassword = password.matches(passwordPattern);

        if (!isValidName) {
            txtName.setStyle("-fx-border-color: red;");
        }

        if (!isValidPassword) {
            txtPassword.setStyle("-fx-border-color: red;");
        }

        if (isValidName && isValidPassword) {
            if (username.equals("user") && password.equals("1234")) {
                Loginanc1.getChildren().clear();
                try {
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
                    Loginanc1.getChildren().add(load);
                } catch (IOException io) {
                    new Alert(Alert.AlertType.ERROR, "Home Page Not Found").show();
                    io.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please enter valid username and password").show();
        }
    }

    public void login(ActionEvent actionEvent) {
        String username = txtName.getText();
        String password = txtPassword.getText();

        txtName.setStyle("-fx-border-color: #7367F0;");
        txtPassword.setStyle("-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String passwordPattern = "^[A-Za-z0-9]+$";

        boolean isValidName = username.matches(namePattern);
        boolean isValidPassword = password.matches(passwordPattern);

        if (!isValidName) {
            txtName.setStyle("-fx-border-color: red;");
        }

        if (!isValidPassword) {
            txtPassword.setStyle("-fx-border-color: red;");
        }

        if (isValidName && isValidPassword) {
            if (username.equals("user") && password.equals("1234")) {
                Loginanc1.getChildren().clear();
                try {
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainLayout.fxml"));
                    Loginanc1.getChildren().add(load);
                } catch (IOException io) {
                    new Alert(Alert.AlertType.ERROR, "Home Page Not Found").show();
                    io.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please enter valid username and password").show();
        }
    }
}
