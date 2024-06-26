package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BoFactory;
import org.example.bo.asset.UserBo;
import org.example.model.User;
import org.example.util.BoType;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpFormController implements Initializable {

    @FXML
    private CheckBox cbShowPassword;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtShowPassword;

    @FXML
    private AnchorPane signUpWindow;

    private final UserBo userBo;

    private final SceneSwitchController sceneSwitch;

    public SignUpFormController() {
        this.userBo = BoFactory.getInstance().getBo(BoType.USER);
        this.sceneSwitch = SceneSwitchController.getInstance();
    }

    @FXML
    void BackToLoginOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(signUpWindow,"login-form.fxml");
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        if (
                txtName.getText().isEmpty()
                && txtEmail.getText().isEmpty()
                && txtAddress.getText().isEmpty()
                && txtPassword.getText().isEmpty()
        ) {
            new Alert(Alert.AlertType.ERROR, "Text Fields can't be empty!").show();
        } else {
            String email = txtEmail.getText();
            if (userBo.isValidEmail(email)){
                User user = new User(
                        userBo.generateEmployeeId(),
                        txtName.getText(),
                        email,
                        txtAddress.getText(),
                        userBo.passwordEncrypt(txtPassword.getText())
                );

                userBo.insertUser(user);

                new Alert(Alert.AlertType.INFORMATION, "Sign Up Success").show();
                sceneSwitch.switchScene(signUpWindow,"login-form.fxml");
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Email. Try again...").show();
            }
        }
    }

    @FXML
    void cbShowPasswordOnAction(ActionEvent event) {
        if (cbShowPassword.isSelected()){
            txtShowPassword.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            txtShowPassword.setVisible(true);
        } else {
            txtPassword.setVisible(true);
            txtShowPassword.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
