package Control;

import Model.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerLoginPaziente {
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
    private void bottoneLoginPaziente(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Connection connection = DBManager.getConnection();
        try {
            String patientLoginQuery = "SELECT * FROM Paziente WHERE Username = ? AND Password = ?";
            PreparedStatement stat = connection.prepareStatement(patientLoginQuery);
            stat.setString(1, username);
            stat.setString(2, password);
            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/PazienteDettagli.fxml"));
                    Parent root = loader.load();

                    ControllerDettagliPaziente controller = loader.getController();
                    controller.setUsername(username);
                    controller.initialize();

                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                } catch(Exception ePatient) {
                    ePatient.printStackTrace();
                }
            } else {
                Alert credentialsAlert = new Alert(Alert.AlertType.ERROR);
                credentialsAlert.setTitle("Errore nell'accesso");
                credentialsAlert.setHeaderText(null);
                credentialsAlert.setContentText("Username o password errati :(");
                credentialsAlert.showAndWait();
            }
            rs.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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