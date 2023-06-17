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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerLoginPaziente {

    // Dichiarazione variabili (FXML e non)
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

    // Evento innescato al click sul bottone "Indietro" in PazienteLogin
    @FXML
    private void bottoneIndietro(ActionEvent event) {
        try {
            // Carica il template precedente SceltaLogin.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SceltaLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }

    // Evento innescato al click sul bottone "Login" in PazienteLogin
    @FXML
    private void bottoneLoginPaziente(ActionEvent event) {
        // Prendo i dati dagli input
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Apro la connessione al DB
        Connection connection = DBManager.getConnection();
        try {
            // Query di controllo credenziali
            String patientLoginQuery = "SELECT * FROM Paziente WHERE Username = ? AND Password = ?";
            PreparedStatement stat = connection.prepareStatement(patientLoginQuery);
            stat.setString(1, username);
            stat.setString(2, password);
            ResultSet rs = stat.executeQuery();

            // Se esiste un risultato alla query..
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
            // Se non esiste... (credenziali errate)
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

    // Metodo che permette di effettuare il login premendo INVIO
    // Innescato nel PasswordField
    @FXML
    private void handleInvio(KeyEvent key) {
        if (key.getCode() == KeyCode.ENTER) {
            bottoneLoginPaziente(new ActionEvent());
        }
    }

    // Metodo di inizializzazione
    public void initialize() {
        // CSS Class per lo stile
        titoloPannelloLogin.getStyleClass().add("title-login");
        usernameLabel.getStyleClass().add("username-label");
        passwordLabel.getStyleClass().add("password-label");
        usernameField.getStyleClass().add("username-field");
        passwordField.getStyleClass().add("password-field");
        backButton.getStyleClass().add("back-button");
        loginButton.getStyleClass().add("login-button");
    }

}