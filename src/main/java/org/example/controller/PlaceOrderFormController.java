package org.example.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnCustomerDetails;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnManageEmployee;

    @FXML
    private Button btnOrderDetails;

    @FXML
    private Button btnProductDetails;

    @FXML
    private Button btnSupplierDetails;

    @FXML
    private ComboBox<?> cmbCustomerId;

    @FXML
    private ComboBox<?> cmbItemId;

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



    public void btnDashboardOnAction(ActionEvent actionEvent) {
    }

    public void btnManageEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnOrderDetailsOnAction(ActionEvent actionEvent) {
    }

    public void btnProductDetailsOnAction(ActionEvent actionEvent) {
    }

    public void btnCustomerDetailsOnAction(ActionEvent actionEvent) {
    }

    public void btnSupplierDetailsOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
