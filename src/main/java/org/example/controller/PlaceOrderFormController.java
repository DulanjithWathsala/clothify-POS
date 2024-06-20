package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlaceOrderFormController {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnCustomerDetails;

    @FXML
    private Button btnFinalizeOrder;

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
    private ComboBox<?> cmbCustomerId;

    @FXML
    private ComboBox<?> cmbItemId;

    @FXML
    private Label lblCashier;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtItemQty;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPrice;

    @FXML
    private AnchorPane placeOrderWindow;

    private final SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"customer-details-form.fxml");
    }

    @FXML
    void btnFinalizeOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnManageEmployeeOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"manage-employee-form.fxml");
    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"order-details-form.fxml");
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"place-order-form.fxml");
    }

    @FXML
    void btnProductDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"product-details-form.fxml");
    }

    @FXML
    void btnSupplierDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"supplier-details-form.fxml");
    }

    @FXML
    void lblClothifyMouseClicked(MouseEvent event) throws IOException {
        sceneSwitch.switchScene(placeOrderWindow,"dashboard-form.fxml");
    }

}
