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
    private Button pazientiBottone;
    @FXML
    private Button logoutButton;

    public void initialize() {
        title.getStyleClass().add("title");
        faketextfield1.getStyleClass().add("faketextfield1");
        pazientiBottone.getStyleClass().add("pazientiBottone");
        logoutButton.getStyleClass().add("logout-button");
    }

}
