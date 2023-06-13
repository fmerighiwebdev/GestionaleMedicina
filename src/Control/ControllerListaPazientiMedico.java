package Control;

import Model.Medico;
import Model.MedicoDAO;
import Model.Paziente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

public class ControllerListaPazientiMedico {
    @FXML
    private  TextField title;
    @FXML
    private TextField faketextfield1;
    @FXML
    private HBox hboxButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

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
        logoutButton.getStyleClass().add("logout-button");

        // Name and surname in labels
        // Uso l'username settato per recuperare i dati dalla tabella grazie al modello creato
        MedicoDAO medicoDAO = new MedicoDAO();
        Medico medico = medicoDAO.getDottoreByUsername(username);

        if (medico != null) {
            String name = medico.getName();
            String surname = medico.getSurname();
            nameLabel.setText(name);
            surnameLabel.setText(surname);
        }

        // Bottoni pazienti
        if (medico != null) {
            List<Paziente> pazienti = medicoDAO.getPazientiByMedicoId(medico.getId());

            for (Paziente paziente : pazienti) {
                Button pazientiButton = new Button(paziente.getName() + " " + paziente.getSurname());
                hboxButton.getChildren().add(pazientiButton);
            }
        } else {
            // Gestisci il caso in cui medico sia nullo
            System.out.println("Nessun medico trovato con l'username fornito.");
        }
    }

}
