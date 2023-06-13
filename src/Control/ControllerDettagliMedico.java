package Control;

import Model.Medico;
import Model.Paziente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerDettagliMedico {

    @FXML
    private Button backButton;

    @FXML
    private Label doctorFullName;
    @FXML
    private Label patientName;
    @FXML
    private Label patientSurname;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPaziente(Paziente paziente) {
        String name = paziente.getName();
        String surname = paziente.getSurname();
        patientName.setText(name);
        patientSurname.setText(surname);
    }

    public void setMedico(Medico medico) {
        String name = medico.getName();
        String surname = medico.getSurname();
        doctorFullName.setText(name + " " + surname);
    }

    @FXML
    private void indietroButton(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        // CSS Class
        backButton.getStyleClass().add("back-button");
    }

}
