package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardFormController {
    @FXML
    private Button btnCustomerDetails;

    @FXML
    private Button btnManageEmployee;

    @FXML
    private Button btnOrderDetails;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnProductDetails;

    @FXML
    private Button btnSupplierDetails;

    @FXML
    private Label lblClothify;

    @FXML
    private AnchorPane dashboardWindow;

    private final SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

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
}
