package Control;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerSceltaLogin {

    // Dichiarazione variabili (FXML e non)
    @FXML
    private Label titoloPannelloScelta;
    @FXML
    private Button bottoneSceltaPaziente;
    @FXML
    private Button bottoneSceltaDottore;

    // Evento innescato al click su bottone di scelta "Paziente"
    @FXML
    private void bottoneSceltaPaziente(ActionEvent event) {
        try {
            // Carica il template PazienteLogin.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/PazienteLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) bottoneSceltaPaziente.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }

    // Evento innescato al click sul bottone di scelta "Medico"
    @FXML
    private void bottoneSceltaDottore(ActionEvent event) {
        try {
            // Carica il template MedicoLogin.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MedicoLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) bottoneSceltaDottore.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }

    // metodo di inizializzazione
    public void initialize() {
        // CSS Class per lo stile
        titoloPannelloScelta.getStyleClass().add("title-choice");
        bottoneSceltaPaziente.getStyleClass().add("button-patient-choice");
        bottoneSceltaDottore.getStyleClass().add("button-doctor-choice");
    }

}
