package Control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;

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
        try {
            JsonElement fileElement = new Gson().fromJson(new FileReader("src/Model/user.json"), JsonElement.class);
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray mediciArray = fileObject.getAsJsonArray("medici");
            for (JsonElement medicoElement : mediciArray) {
                JsonObject medicoObject = medicoElement.getAsJsonObject();
                String medicoUsername = medicoObject.get("username").getAsString();
                String medicoPassword = medicoObject.get("password").getAsString();

                if (username.equals(medicoUsername) && password.equals(medicoPassword)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MedicoListaPazienti.fxml"));
                        Parent root = loader.load();

                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (Exception ePatientDetails) {
                        ePatientDetails.printStackTrace();
                    }
                    return;
                }
            }

            // Le credenziali sono errate, mostra una notifica
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di login");
            alert.setHeaderText(null);
            alert.setContentText("Credenziali errate. Riprova.");
            alert.showAndWait();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Paziente login CSS
    public void initialize() {
        titoloPannelloLogin.getStyleClass().add("title-login");
        usernameLabel.getStyleClass().add("username-label");
        passwordLabel.getStyleClass().add("password-label");
        usernameField.getStyleClass().add("username-field");
        passwordField.getStyleClass().add("password-field");
        backButton.getStyleClass().add("back-button");
        loginButton.getStyleClass().add("login-button");
    }

}