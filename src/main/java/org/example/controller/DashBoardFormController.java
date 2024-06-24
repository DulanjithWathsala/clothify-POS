package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Optional;

public class DashBoardFormController {

    @FXML
    private AnchorPane dashboardWindow;

    private final SceneSwitchController sceneSwitch;
    public DashBoardFormController() {
        this.sceneSwitch = SceneSwitchController.getInstance();

    }

    @FXML
    void btnCustomerDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(dashboardWindow,"customer-details-form.fxml");
    }

    @FXML
    void btnManageEmployeeOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(dashboardWindow,"manage-employee-form.fxml");
    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(dashboardWindow,"order-details-form.fxml");
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(dashboardWindow,"place-order-form.fxml");
    }

    @FXML
    void btnProductDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(dashboardWindow,"product-details-form.fxml");
    }

    @FXML
    void btnSupplierDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(dashboardWindow,"supplier-details-form.fxml");
    }

    @FXML
    void lblClothifyMouseClicked(MouseEvent event) throws IOException {
        sceneSwitch.switchScene(dashboardWindow,"dashboard-form.fxml");
    }

    @FXML
    void btnSignOutOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Sign out?");
        Optional<ButtonType> result = alert.showAndWait();
        // Check if the response was OK or Cancel
        if (result.isPresent() && result.get() == ButtonType.OK) {
            sceneSwitch.switchScene(dashboardWindow,"login-form.fxml");
            new Alert(Alert.AlertType.INFORMATION, "Sign out successfully").show();
        }
    }
}
