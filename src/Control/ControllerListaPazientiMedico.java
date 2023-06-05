package Control;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ControllerListaPazientiMedico {
    @FXML
    private  TextField title;
    @FXML
    private TextField faketextfield1;
    @FXML
    private TextField faketextfield2;
    public void initialize() {
        title.getStyleClass().add("title");
        faketextfield1.getStyleClass().add("faketextfield1");
        faketextfield2.getStyleClass().add("faketextfield2");
    }

}
