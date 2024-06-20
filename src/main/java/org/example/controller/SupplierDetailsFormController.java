package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.asset.impl.SupplierBoImpl;
import org.example.model.Supplier;
import org.example.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierDetailsFormController implements Initializable {

    @FXML
    private Button btnAction;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnCustomerDetails;

    @FXML
    private Button btnDelete;

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
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colSupplierCompany;

    @FXML
    private TableColumn<?, ?> colSupplierEmail;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private Label lblClothify;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TextField txtSupplierCompany;

    @FXML
    private TextField txtSupplierEmail;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private AnchorPane supplierWindow;

    private final SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    private String currentId;

    private final SupplierBoImpl supplierBo = new SupplierBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtSupplierId.setEditable(false);
        loadSupplierId();

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        loadSupplierTbl();
    }

    private void clearTextFields(){
        txtSupplierName.setText("");
        txtSupplierEmail.setText("");
        txtSupplierCompany.setText("");
    }

    private void loadSupplierId(){
        currentId = supplierBo.generateSupplierId();
        txtSupplierId.setText(currentId);
    }

    private void loadSupplierTbl(){
        tblSupplier.setItems(supplierBo.getAllSuppliers());
    }

    @FXML
    void btnActionOnAction(ActionEvent event) {
        txtSupplierId.setEditable(true);
        txtSupplierId.setText("");
        btnAdd.setVisible(false);
        btnClear.setVisible(false);
        btnAction.setVisible(false);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String email = txtSupplierEmail.getText();
        if (supplierBo.isValidEmail(email)){
            Supplier supplier = new Supplier(
                    currentId,
                    txtSupplierName.getText(),
                    email,
                    txtSupplierCompany.getText()
            );

            supplierBo.addSupplier(supplier);

            new Alert(Alert.AlertType.INFORMATION, "Supplier Added Successfully").show();

            clearTextFields();
            loadSupplierId();
            loadSupplierTbl();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Email. Try again...").show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFields();
    }

    @FXML
    void btnCustomerDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(supplierWindow,"customer-details-form.fxml");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to proceed?");
        Optional<ButtonType> result = alert.showAndWait();
        // Check if the response was OK or Cancel
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (supplierBo.deleteSupplierById(txtSupplierId.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Delete failed. Try again...").show();
            }
        }
        loadSupplierTbl();
        clearTextFields();
    }

    @FXML
    void btnManageEmployeeOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(supplierWindow,"manage-employee-form.fxml");
    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(supplierWindow,"order-details-form.fxml");
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(supplierWindow,"place-order-form.fxml");
    }

    @FXML
    void btnProductDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(supplierWindow,"product-details-form.fxml");
    }

    @FXML
    void btnSupplierDetailsOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(supplierWindow,"supplier-details-form.fxml");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Supplier supplier = supplierBo.getSupplierById(txtSupplierId.getText());

        if (supplier != null){
            supplier.setName(txtSupplierName.getText());
            supplier.setEmail(txtSupplierEmail.getText());
            supplier.setCompany(txtSupplierCompany.getText());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to proceed?");
            Optional<ButtonType> result = alert.showAndWait();
            // Check if the response was OK or Cancel
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (supplierBo.updateSupplier(supplier)) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Updated Successfully").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Update failed. Try again...").show();
                }
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Supplier does not exists...").show();
        }
        loadSupplierTbl();
        clearTextFields();
    }

    @FXML
    void lblClothifyClickEvent(MouseEvent event) throws IOException {
        sceneSwitch.switchScene(supplierWindow,"dashboard-form.fxml");
    }

}
