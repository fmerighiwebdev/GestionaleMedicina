package Control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import Model.PazienteDAO;
import Model.Paziente;

import java.sql.*;

public class ControllerDettagliPaziente {

    @FXML
    private TextField title;
    @FXML
    private Button logoutButton;
    @FXML
    private Button sendButton;
    @FXML
    private TextField faketextfield1;

    @FXML
    private Label fullName;

    private String username;

    // Setto l'username preso dal controller di login paziente
    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    private void bottoneLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/PazienteLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            // Logout effettuato con successo
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout: " + username);
            alert.setHeaderText(null);
            alert.setContentText("Logout eseguito con successo");
            alert.showAndWait();
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // CSS Class
        title.getStyleClass().add("title");
        logoutButton.getStyleClass().add("logout-button");
        sendButton.getStyleClass().add("send-button");
        faketextfield1.getStyleClass().add("faketextfield1");

        // Name and surname in label
        // Uso l'username settato per recuperare i dati dalla tabella grazie alla classe creata
        PazienteDAO pazienteDAO = new PazienteDAO();
        Paziente paziente = pazienteDAO.getPazienteByUsername(username);

        if (paziente != null) {
            String name = paziente.getName();
            String surname = paziente.getSurname();
            fullName.setText(name + " " + surname);
        }

    }

}
