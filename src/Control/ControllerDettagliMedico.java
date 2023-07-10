package Control;

import Model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ControllerDettagliMedico {

    // Dichiarazione variabili (FXML e non)
    @FXML
    private Button backButton;
    @FXML
    private Button sendButton;
    @FXML
    private Label doctorFullName;
    @FXML
    private Label patientName;
    @FXML
    private Label patientSymptoms;
    @FXML
    private Label patientInformations;
    @FXML
    private Label patientSurname;
    @FXML
    private Label todayDate;

    @FXML
    private TextField faketextfield1;
    @FXML
    private TextField faketextfield2;
    @FXML
    private TextField faketextfield3;
    @FXML
    private TextField faketextfield4;
    @FXML
    private TextField faketextfield5;

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

    @FXML
    private TableView<Rilevazioni> rilevationsTable;
    @FXML
    private TableColumn<Rilevazioni, String> dataColumn;
    @FXML
    private TableColumn<Rilevazioni, Integer> sbpColumn;
    @FXML
    private TableColumn<Rilevazioni, Integer> dbpColumn;

    private ObservableList<Rilevazioni> obsRilevazioniList;

    private String username;
    private Paziente paziente;
    private LocalDate date;

    // Set username
    public void setUsername(String username) {
        this.username = username;
    }

    // Set Paziente
    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
        String name = paziente.getName();
        String surname = paziente.getSurname();
        patientName.setText(name);
        patientSurname.setText(surname);

        String patientSymptomsText = paziente.getSymptoms();
        patientSymptoms.setText(patientSymptomsText);

        String patientInformationText = paziente.getInfo();
        patientInformations.setText(patientInformationText);

        PazienteDAO pazienteDAO = new PazienteDAO();
        List<Rilevazioni> rilevazioniList = pazienteDAO.getRilevazioneByPazienteID(paziente.getId());

        obsRilevazioniList.clear();
        obsRilevazioniList.addAll(rilevazioniList);
        rilevationsTable.setItems(obsRilevazioniList);

        checkLastRilevationDate();
    }

    // Set Medico
    public void setMedico(Medico medico) {
        String name = medico.getName();
        String surname = medico.getSurname();
        doctorFullName.setText(name + " " + surname);
    }

    // Evento innescato al click sul bottone "Cambia..." nel pannello MedicoDettagli
    @FXML
    private void indietroButton(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    // Evento innescato sul bottone "Invia" nel pannello MedicoDettagli
    @FXML
    private void sendTherapy(ActionEvent event) {
        // Prende i dati dai TextField
        String medTherapy = medTherapyTextF.getText();
        String assTherapy = assTherapyTextF.getText();
        String quantityTherapy = quantityTherapyTextF.getText();
        String indTherapy = indTherapyTextF.getText();
        String info = infoTextA.getText();

        // Controllo sui valori - campo vuoto
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
        // Il campo "Informazioni" può essere VUOTO
        // Se viene lasciato vuoto, viene mostrato un alert che chiede una conferma (SI / NO)
        // Se il medico preme "SI" i dati vengono inviati comunque, senza aggiornare / inserire info
        // Se il medico preme "NO" ritorna alla compilazione e non viene inviato nulla
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

        // Controllo sui valori - valore errato
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

        // Creo il Data Access Object TerapiaDAO e l'oggetto Terapia
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

        // Creo il Data Access Object PazienteDAO
        // Chiamo il metodo all'interno dell'oggetto per aggiornare le info del paziente
        PazienteDAO pazienteDAO = new PazienteDAO();
        pazienteDAO.updatePazienteInfo(paziente.getUsername(), info);

        // Invio dei dati eseguito
        Alert sendSuccessfull = new Alert(Alert.AlertType.CONFIRMATION);
        sendSuccessfull.setTitle("Invio completato");
        sendSuccessfull.setHeaderText(null);
        sendSuccessfull.setContentText("I dati sono stati inviati correttamente al database");
        sendSuccessfull.showAndWait();
    }

    // Metodo che controlla se l'utente non inserisce una rilevazione da tre giorni o più
    private void checkLastRilevationDate() {
        RilevazioniDAO rilevazioniDAO = new RilevazioniDAO();
        LocalDate today = LocalDate.now();

        LocalDate threeDaysAgo = today.minusDays(3);

        List<Rilevazioni> rilevazioniList = rilevazioniDAO.getRilevazioneByPazienteID(paziente.getId());

        boolean hasRecentRilevations = false;

        for (Rilevazioni rilevazione : rilevazioniList) {
            LocalDate rilevationDate = LocalDate.parse(rilevazione.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (rilevationDate.isAfter(threeDaysAgo) || rilevationDate.isEqual(threeDaysAgo)) {
                hasRecentRilevations = true;
                break;
            }
        }

        if (!hasRecentRilevations) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attenzione");
                alert.setHeaderText(null);
                alert.setContentText("Il paziente non ha inserito una rilevazione per più di 3 giorni.");
                alert.showAndWait();
            });
        }
    }

    // Metodo di inizializzazione
    @FXML
    public void initialize() {
        // CSS Class per lo stile degli elementi
        backButton.getStyleClass().add("back-button");
        sendButton.getStyleClass().add("send-button");
        faketextfield1.getStyleClass().add("faketextfield1");
        faketextfield2.getStyleClass().add("faketextfield2");
        faketextfield3.getStyleClass().add("faketextfield3");
        faketextfield4.getStyleClass().add("faketextfield4-patient");
        faketextfield5.getStyleClass().add("faketextfield5-patient");
        todayDate.getStyleClass().add("today-date");

        medTherapyTextF.getStyleClass().add("doctor-details-text-field");
        assTherapyTextF.getStyleClass().add("doctor-details-text-field");
        quantityTherapyTextF.getStyleClass().add("doctor-details-text-field");
        indTherapyTextF.getStyleClass().add("doctor-details-text-field");
        infoTextA.getStyleClass().add("doctor-details-text-field");
        patientSymptoms.getStyleClass().add("symp-info-label");
        patientInformations.getStyleClass().add("symp-info-label");

        obsRilevazioniList = FXCollections.observableArrayList();

        dataColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        sbpColumn.setCellValueFactory(new PropertyValueFactory<>("sbp"));
        dbpColumn.setCellValueFactory(new PropertyValueFactory<>("dbp"));

        // Imposto uno stile per le varie soglie di valori nella TableView
        // Per SBP e DBP
        sbpColumn.setCellFactory(column -> new TableCell<Rilevazioni, Integer>() {
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setText(item.toString());

                    if (item < 120) {
                        getStyleClass().add("optimal-value");
                    } else if (item >= 120 && item < 140) {
                        getStyleClass().add("normal-value");
                    } else if (item >= 140 && item < 180) {
                        getStyleClass().add("warning-value");
                    } else {
                        getStyleClass().add("danger-value");
                    }
                }
            }
        });

        dbpColumn.setCellFactory(column -> new TableCell<Rilevazioni, Integer>() {
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setText(item.toString());

                    if (item < 80) {
                        getStyleClass().add("optimal-value");
                    } else if (item >= 80 && item < 90) {
                        getStyleClass().add("normal-value");
                    } else if (item >= 90 && item < 110) {
                        getStyleClass().add("warning-value");
                    } else {
                        getStyleClass().add("danger-value");
                    }
                }
            }
        });

        // Label con data di oggi
        date = LocalDate.now();
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        String formattedDate = String.format("%02d/%02d/%04d", day, month, year);

        todayDate.setText(formattedDate);
    }
}


