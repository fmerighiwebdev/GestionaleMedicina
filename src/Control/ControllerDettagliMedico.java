package Control;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class ControllerDettagliMedico {

    @FXML
    private Button backButton;
    @FXML
    private Button sendButton;
    @FXML
    private Label doctorFullName;
    @FXML
    private Label patientName;
    @FXML
    private Label patientSurname;

    @FXML
    private TextField medTherapyTextF;
    @FXML
    private TextField assTherapyTextF;
    @FXML
    private TextField quantityTherapyTextF;
    @FXML
    private TextField indTherapyTextF;
    @FXML
    private TextArea infoTextA;

    private String username;
    private Paziente paziente;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
        String name = paziente.getName();
        String surname = paziente.getSurname();
        patientName.setText(name);
        patientSurname.setText(surname);
    }

    public void setMedico(Medico medico) {
        String name = medico.getName();
        String surname = medico.getSurname();
        doctorFullName.setText(name + " " + surname);
    }

    @FXML
    private void indietroButton(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void sendTherapy(ActionEvent event) {
        String medTherapy = medTherapyTextF.getText();
        String assTherapy = assTherapyTextF.getText();
        String quantityTherapy = quantityTherapyTextF.getText();
        String indTherapy = indTherapyTextF.getText();
        String info = infoTextA.getText();

        if (medTherapy.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci il farmaco da assumere durante la terapia");
            isEmptyAlert.showAndWait();
            return;
        } else if (assTherapy.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci il n° di assunzioni del farmaco");
            isEmptyAlert.showAndWait();
            return;
        } else if (quantityTherapy.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci la quantità di farmaco da assumere durante la terapia");
            isEmptyAlert.showAndWait();
            return;
        } else if (indTherapy.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci eventuali indicazioni riguardanti la terapia");
            isEmptyAlert.showAndWait();
            return;
        } else if (info.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Conferma invio");
            isEmptyAlert.setHeaderText(null);

            String message = "Il campo informazioni è VUOTO.\n" +
                    "Vuoi inviare comunque i dati?\n" +
                    "Le informazioni del paziente non verranno aggiunte / aggiornate.";

            isEmptyAlert.getDialogPane().setContentText(message);

            ButtonType buttonTypeSi = new ButtonType("Si");
            ButtonType buttonTypeNo = new ButtonType("No");

            isEmptyAlert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo);

            Optional<ButtonType> res = isEmptyAlert.showAndWait();

            if (res.isPresent() && res.get() == buttonTypeNo) {
                return;
            }
        }

        try {
            int assIntVal = Integer.parseInt(assTherapy);
            int quantityIntVal = Integer.parseInt(quantityTherapy);
        } catch (NumberFormatException eNumber) {
            Alert isWrongAlert = new Alert(Alert.AlertType.ERROR);
            isWrongAlert.setTitle("Errore in input");
            isWrongAlert.setHeaderText(null);
            isWrongAlert.setContentText("Inserisci un valore numerico valido per n°assunzioni e/o quantità");
            isWrongAlert.showAndWait();
            return;
        }

        TerapiaDAO terapiaDAO = new TerapiaDAO();
        int assIntVal = Integer.parseInt(assTherapy);
        int quantityIntVal = Integer.parseInt(quantityTherapy);
        Terapia terapia = new Terapia(medTherapy, assIntVal, quantityIntVal, indTherapy, paziente.getId());

        // Controllo se esiste già una terapia per il paziente
        boolean terapiaEsistente = terapiaDAO.terapiaExist(paziente.getId());

        if (terapiaEsistente) {
            // Aggiorna la tabella se era già presente una voce per il paziente
            terapiaDAO.updateTerapia(terapia);
        } else {
            // Inserisce una voce alla tabella se non era presente per il paziente
            terapiaDAO.insertTerapia(terapia);
        }

        PazienteDAO pazienteDAO = new PazienteDAO();
        pazienteDAO.updatePazienteInfo(paziente.getUsername(), info);

        // Invio eseguito
        Alert sendSuccessfull = new Alert(Alert.AlertType.CONFIRMATION);
        sendSuccessfull.setTitle("Invio completato");
        sendSuccessfull.setHeaderText(null);
        sendSuccessfull.setContentText("I dati sono stati inviati correttamente al database");
        sendSuccessfull.showAndWait();
    }

    @FXML
    public void initialize() {
        // CSS Class
        backButton.getStyleClass().add("back-button");
        sendButton.getStyleClass().add("send-button");
    }

}
