package Control;

import Model.DBManager;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

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

        Connection connection = DBManager.getConnection();
        try {
            String patientLoginQuery = "SELECT * FROM Dottore WHERE Username = ? AND Password = ?";
            PreparedStatement stat = connection.prepareStatement(patientLoginQuery);
            stat.setString(1, username);
            stat.setString(2, password);
            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MedicoListaPazienti.fxml"));
                    Parent root = loader.load();

                    ControllerListaPazientiMedico controller = loader.getController();
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