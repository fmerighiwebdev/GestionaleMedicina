package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerListaPazientiMedico {
    @FXML
    private  TextField title;
    @FXML
    private TextField faketextfield1;
    @FXML
    private Button pazientiBottone;
    @FXML
    private Button logoutButton;

    @FXML
    private void bottoneLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MedicoLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            // Logout effettuato con successo
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText(null);
            alert.setContentText("Logout eseguito con successo");
            alert.showAndWait();
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }

    public void initialize() {
        // CSS Class
        title.getStyleClass().add("title");
        faketextfield1.getStyleClass().add("faketextfield1");
        pazientiBottone.getStyleClass().add("pazientiBottone");
        logoutButton.getStyleClass().add("logout-button");
    }

}
