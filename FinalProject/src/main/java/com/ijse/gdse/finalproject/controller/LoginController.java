package com.ijse.gdse.finalproject.controller;

import com.ijse.gdse.finalproject.bo.BOFactory;
import com.ijse.gdse.finalproject.bo.custom.LoginBO;
import com.ijse.gdse.finalproject.dto.LoginDTO;
import com.ijse.gdse.finalproject.entity.Login;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {
    public TextField txtUserName;
    public TextField txtPassword;
    public AnchorPane ancLoginPage;
    public Button btnRegister;

    LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOType.LOGIN);

    public void initialize() {
        // Add listeners to both TextFields
        txtUserName.textProperty().addListener(this::validateCredentials);
        txtPassword.textProperty().addListener(this::validateCredentials);
    }

    private void validateCredentials(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        // If both fields are non-empty, try logging in
        if (!txtUserName.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            try {
                ArrayList<LoginDTO> loginDTOS = loginBO.getAllIdAndPassword();

                for (LoginDTO login : loginDTOS) {
                    if (login.getUsername().equals(txtUserName.getText()) &&
                            login.getPassword().equals(txtPassword.getText())) {
                        goToMainpage(login.getUsername());
                        return;
                    }
                }

                // If login is invalid, handle it
                handleInvalidCredentials();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInvalidCredentials() {
        if (txtUserName.getText().isEmpty()) {
            txtUserName.setStyle("-fx-border-color: transparent;");
        } else {
            txtUserName.setStyle("-fx-border-color: red;");
        }



        if (txtPassword.getText().isEmpty()) {
            txtPassword.setStyle("-fx-border-color: transparent;");
        } else {
            txtPassword.setStyle("-fx-border-color: red;");
        }

    }



    public void loginOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        validateCredentials(null, null, null);
    }



    private void goToMainpage(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePage.fxml"));
            Parent load = loader.load();

            HomePageController controller = loader.getController();
            controller.setData(username);

            ancLoginPage.getChildren().clear();
            ancLoginPage.getChildren().add(load);

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot load MainLayout");
            alert.showAndWait();
        }
    }

    public void registerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/User.fxml"));
        ancLoginPage.getChildren().clear();
        ancLoginPage.getChildren().add(load);
    }

}
