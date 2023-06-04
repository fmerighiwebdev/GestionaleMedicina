package Control;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ControllerListaPazientiMedico {
    @FXML
    private TitledPane titledPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Label info;
    @FXML
    private Label nomeMedico;
    @FXML
    private Label cognomeMedico;
    @FXML
    private Label tuoiPazienti;
    @FXML
    private Button paziente1;
    @FXML
    private Button paziente2;

    public void initialize() {
        titledPane.getStyleClass().add("titled-pane-lista");
        anchorPane.getStyleClass().add("anchor-pane-lista");
        splitPane.getStyleClass().add("split-pane-lista");
        info.getStyleClass().add("title-info");
        nomeMedico.getStyleClass().add("nome-medico-label");
        cognomeMedico.getStyleClass().add("cognome-medico-label");
        tuoiPazienti.getStyleClass().add("tuoi-pazienti-label");
        paziente1.getStyleClass().add("bottone-paziente1");
        paziente2.getStyleClass().add("bottone-paziente2");
    }

}
