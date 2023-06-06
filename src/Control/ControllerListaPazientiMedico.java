package Control;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ControllerListaPazientiMedico {
    @FXML
    private  TextField title;
    @FXML
    private TextField faketextfield1;
    public void initialize() {
        title.getStyleClass().add("title");
        faketextfield1.getStyleClass().add("faketextfield1");
    }

}
