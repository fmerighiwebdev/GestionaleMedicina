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

    @FXML
    private Label titoloPannelloScelta;
    @FXML
    private Button bottoneSceltaPaziente;
    @FXML
    private Button bottoneSceltaDottore;

    @FXML
    private void bottoneSceltaPaziente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/PazienteLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) bottoneSceltaPaziente.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }

    @FXML
    private void bottoneSceltaDottore(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MedicoLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) bottoneSceltaDottore.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }

    public void initialize() {
        // CSS Class
        titoloPannelloScelta.getStyleClass().add("title-choice");
        bottoneSceltaPaziente.getStyleClass().add("button-patient-choice");
        bottoneSceltaDottore.getStyleClass().add("button-doctor-choice");
    }

}
