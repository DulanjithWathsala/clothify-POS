package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;
import org.example.bo.asset.impl.UserBoImpl;
import org.example.model.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ManageEmployeeFormController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCustomerDetails;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnManageEmployee;

    @FXML
    private Button btnOrderDetails;

    @FXML
    private Button btnProductDetails;

    @FXML
    private Button btnSupplierDetails;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtEmployeeAddress;

    @FXML
    private TextField txtEmployeeEmail;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeName;

    private final UserBoImpl userBo = new UserBoImpl();

    private String currentId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtEmployeeId.setEditable(false);
        this.currentId = userBo.generateEmployeeId();
    }

    public void btnAddOnAction(ActionEvent actionEvent){
        User user = new User(
                currentId,
                txtEmployeeName.getText(),
                txtEmployeeEmail.getText(),
                "Dula@123",
                txtEmployeeAddress.getText()
        );

        userBo.insertUser(user);

        new Alert(Alert.AlertType.INFORMATION, "Employee Added Successfully").show();
        clearTextFields();
    }

    private void clearTextFields(){
        txtEmployeeId.setText("");
        txtEmployeeName.setText("");
        txtEmployeeEmail.setText("");
        txtEmployeeAddress.setText("");
    }

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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
