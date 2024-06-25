package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BoFactory;
import org.example.bo.asset.UserBo;
import org.example.entitiy.UserEntity;
import org.example.model.User;
import org.example.util.BoType;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgotPasswordFormController implements Initializable {

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSendOtp;

    @FXML
    private CheckBox cbShowPassword;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfReEnterPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtOtp;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtReEnterPassword;

    @FXML
    private AnchorPane forgotPasswordWindow;

    private final UserBo userBo;

    private final SceneSwitchController sceneSwitch;

    private int otp;

    public ForgotPasswordFormController() {
        this.userBo = BoFactory.getInstance().getBo(BoType.USER);
        this.sceneSwitch = SceneSwitchController.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnReset.setDisable(true);
        pfPassword.setEditable(false);
        pfReEnterPassword.setEditable(false);
    }

    @FXML
    void BackToLoginOnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(forgotPasswordWindow,"login-form.fxml");
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws IOException {
        if (
                pfPassword.getText().isEmpty()
                && pfReEnterPassword.getText().isEmpty()
                && txtOtp.getText().isEmpty()
        ) {
            new Alert(Alert.AlertType.ERROR, "Input fields can't be empty!").show();
        } else {
            if (pfPassword.getText().equals(pfReEnterPassword.getText())) {
                if (Integer.parseInt(txtOtp.getText()) == otp) {
                    if (
                            userBo.updatePasswordByEmail(txtEmail.getText(),
                                    userBo.passwordEncrypt(txtPassword.getText()))

                    ) {
                        new Alert(Alert.AlertType.INFORMATION, "Password Reset Successfully").show();
                        sceneSwitch.switchScene(forgotPasswordWindow,"login-form.fxml");
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Password couldn't reset").show();
                    }
                }
            }
        }

    }

    @FXML
    void btnSendOtpOnAction(ActionEvent event) {
        if (!txtEmail.getText().isEmpty()) {
            Random random = new Random();
            otp = random.nextInt(999999)+100000;

            try {
                userBo.sendEmail(txtEmail.getText(), Integer.toString(otp));
                new Alert(Alert.AlertType.INFORMATION,"Send Email Successfully").show();
                btnReset.setDisable(false);
                pfPassword.setEditable(true);
                pfReEnterPassword.setEditable(true);
            } catch (MessagingException e) {
                new Alert(Alert.AlertType.ERROR,"Access denied... your Email is invalid").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Input fields can't be empty!").show();
        }
    }

    @FXML
    void cbShowPasswordOnAction(ActionEvent event) {
        if (cbShowPassword.isSelected()){
            txtPassword.setText(pfPassword.getText());
            pfPassword.setVisible(false);
            txtReEnterPassword.setText(pfReEnterPassword.getText());
            pfReEnterPassword.setVisible(false);
        } else {
            pfPassword.setVisible(true);
            pfReEnterPassword.setVisible(true);
        }
    }
}
