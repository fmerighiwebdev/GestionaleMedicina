package Control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerDettagliMedico {

    @FXML
    private Label titoloDettagli;

    @FXML
    public void initialize() {
        titoloDettagli.getStyleClass().add("details-title");
    }

}
