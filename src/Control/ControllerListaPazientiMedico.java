package Control;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

public class ControllerListaPazientiMedico {

    // Dichiarazione variabili (FXML e non)
    @FXML
    private  TextField title;
    @FXML
    private TextField faketextfield1;
    @FXML
    private TextField faketextfield2;
    @FXML
    private FlowPane hboxButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;

    private String username;

    MedicoFactory medicoFactory = new DefaultMedicoFactory();

    // Set username
    public void setUsername(String username) {
        this.username = username;
    }

    // Evento innescato al click sul bottone "Logout" in MedicoListaPazienti
    @FXML
    private void bottoneLogout(ActionEvent event) {
        try {
            // Carica il template precedente MedicoLogin.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MedicoLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            // Logout effettuato con successo
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout" + username);
            alert.setHeaderText(null);
            alert.setContentText("Logout eseguito con successo");
            alert.showAndWait();
        } catch (Exception ePatientLogin) {
            ePatientLogin.printStackTrace();
        }
    }

    // Metodo di inizializzazione
    public void initialize() {
        // CSS Class per lo stile
        title.getStyleClass().add("title");
        faketextfield1.getStyleClass().add("faketextfield1");
        faketextfield2.getStyleClass().add("faketextfield2");
        logoutButton.getStyleClass().add("logout-button");

        // Name and surname in label
        // Uso l'username settato per recuperare i dati dalla tabella grazie al modello creato
        MedicoDAO medicoDAO = new MedicoDAO();
        Medico medico = medicoDAO.getDottoreByUsername(username);

        if (medico != null) {
            String name = medico.getName();
            String surname = medico.getSurname();
            nameLabel.setText(name);
            surnameLabel.setText(surname);
        }

        // Bottoni "dinamici" pazienti
        if (medico != null) {
            // Creo una lista con TUTTI i pazienti presenti
            List<Paziente> pazienti = medicoDAO.getPazienti();

            // Per ogni paziente viene generato un bottone accessibile
            for (Paziente paziente : pazienti) {
                ImageView Icon = new ImageView((new Image("/View/images/cartel_32.png")));
                Button pazientiButton = new Button(paziente.getName() + " " + paziente.getSurname());

                // Stile dei bottoni generati
                pazientiButton.setStyle("-fx-background-color: #D9D9D9; " +
                        "-fx-padding: 10px 15px; " +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-cursor: hand;");

                pazientiButton.setOnMouseEntered(e ->{
                    pazientiButton.setStyle("-fx-background-color: #a1a1a1; " +
                            "-fx-padding: 10px 15px; " +
                            "-fx-background-radius: 20px;" +
                            "-fx-border-radius: 10px;" +
                            "-fx-cursor: hand;");
                });
                pazientiButton.setOnMouseExited(e -> {
                    pazientiButton.setStyle("-fx-background-color: #D9D9D9; " +
                            "-fx-padding: 10px 15px; " +
                            "-fx-background-radius: 20px;" +
                            "-fx-border-radius: 10px;" +
                            "-fx-cursor: hand;");
                });
                pazientiButton.setGraphic(Icon);

                hboxButton.setHgap(10);
                hboxButton.setVgap(10);
                hboxButton.setPrefWrapLength(400);

                hboxButton.getChildren().add(pazientiButton);

                // Evento innescato al click su un bottone di un paziente
                pazientiButton.setOnAction(event -> {
                    try {
                        // Carica il template MedicoDettagli.fxml
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MedicoDettagli.fxml"));
                        Parent root = loader.load();
                        ControllerDettagliMedico controller = loader.getController();
                        // Imposta i dati del paziente nel relativo controller
                        controller.setPaziente(paziente);
                        controller.setMedico(medico);

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                        stage.setResizable(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

}
