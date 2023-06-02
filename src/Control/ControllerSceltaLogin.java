package Control;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerSceltaLogin {

    @FXML
    private void bottoneSceltaPaziente(ActionEvent event) {
    }

    @FXML
    private void bottoneSceltaDottore(ActionEvent event) {
    }

    @FXML
    private Label titoloPannelloScelta;
    @FXML
    private Button bottoneSceltaPaziente;
    @FXML
    private Button bottoneSceltaDottore;
    public void initialize() {
        titoloPannelloScelta.getStyleClass().add("title-choice");
        bottoneSceltaPaziente.getStyleClass().add("button-patient-choice");
        bottoneSceltaDottore.getStyleClass().add("button-doctor-choice");
    }

}
