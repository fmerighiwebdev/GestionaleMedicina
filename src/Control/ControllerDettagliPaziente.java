package Control;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerDettagliPaziente {

    @FXML
    private TextField title;

    public void initialize() {
        title.getStyleClass().add("title");
    }

}
