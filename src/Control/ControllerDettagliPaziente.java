package Control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerDettagliPaziente {

    @FXML
    private TextField title;
    @FXML
    private Button logoutButton;
    @FXML
    private Button sendButton;
    @FXML
    private TextField faketextfield1;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    private void bottoneLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/PazienteLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            // Logout effettuato con successo
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout: " + username);
            alert.setHeaderText(null);
            alert.setContentText("Logout eseguito con successo");
            alert.showAndWait();
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }

    public void initialize() {
        title.getStyleClass().add("title");
        logoutButton.getStyleClass().add("logout-button");
        sendButton.getStyleClass().add("send-button");
        faketextfield1.getStyleClass().add("faketextfield1");
    }

}
