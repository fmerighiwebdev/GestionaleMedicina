package Control;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerLoginMedico {
    @FXML
    private Label titoloPannelloLogin;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button backButton;
    @FXML
    private Button loginButton;

    @FXML
    private void bottoneIndietro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SceltaLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }
    @FXML
    private void bottoneLoginMedico(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
    }

    public void initialize() {
        // CSS Class
        titoloPannelloLogin.getStyleClass().add("title-login");
        usernameLabel.getStyleClass().add("username-label");
        passwordLabel.getStyleClass().add("password-label");
        usernameField.getStyleClass().add("username-field");
        passwordField.getStyleClass().add("password-field");
        backButton.getStyleClass().add("back-button");
        loginButton.getStyleClass().add("login-button");
    }

}