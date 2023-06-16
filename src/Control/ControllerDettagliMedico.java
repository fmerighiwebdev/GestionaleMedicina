package Control;

import Model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import java.util.Date;
import java.util.List;
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

    private String username;
    private Paziente paziente;
    private LocalDate date;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
        String name = paziente.getName();
        String surname = paziente.getSurname();
        patientName.setText(name);
        patientSurname.setText(surname);

        PazienteDAO pazienteDAO = new PazienteDAO();
        List<Rilevazioni> rilevazioniList = pazienteDAO.getRilevazioneByPazienteID(paziente.getId());
        rilevationsTable.setItems(FXCollections.observableList(rilevazioniList));

        checkLastRilevationDate();
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

    // Metodo che controlla se l'utente non inserisce una rilevazione da 3 giorni o più
    private void checkLastRilevationDate() {
        RilevazioniDAO rilevazioniDAO = new RilevazioniDAO();
        LocalDate today = LocalDate.now();

        LocalDate threeDaysAgo = today.minusDays(3);

        List<Rilevazioni> rilevazioniList = rilevazioniDAO.getRilevazioneByPazienteID(paziente.getId());
        if (!rilevazioniList.isEmpty()) {
            Rilevazioni lastRilevation = rilevazioniList.get(rilevazioniList.size() - 1);
            String lastRilevationDateStr = lastRilevation.getDate();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date lastRilevationDate = dateFormat.parse(lastRilevationDateStr);
                LocalDate lastRilevationLocalDate = lastRilevationDate.toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();

                if (lastRilevationLocalDate.isBefore(threeDaysAgo)) {
                    // Questo permette di eseguire l'alert dopo il caricamento del pannello
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attenzione");
                        alert.setHeaderText(null);
                        alert.setContentText("Il paziente non ha inserito una rilevazione negli ultimi 3 giorni.");
                        alert.showAndWait();
                    });
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            // Questo permette di eseguire l'alert dopo il caricamento del pannello
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attenzione");
                alert.setHeaderText(null);
                alert.setContentText("Il paziente non ha inserito alcuna rilevazione al momento.");
                alert.showAndWait();
            });
        }
    }

    @FXML
    public void initialize() {
        // CSS Class
        backButton.getStyleClass().add("back-button");
        sendButton.getStyleClass().add("send-button");
        faketextfield1.getStyleClass().add("faketextfield1");
        faketextfield2.getStyleClass().add("faketextfield2");
        faketextfield3.getStyleClass().add("faketextfield3");
        faketextfield4.getStyleClass().add("faketextfield4-patient");
        todayDate.getStyleClass().add("today-date");

        medTherapyTextF.getStyleClass().add("doctor-details-text-field");
        assTherapyTextF.getStyleClass().add("doctor-details-text-field");
        quantityTherapyTextF.getStyleClass().add("doctor-details-text-field");
        indTherapyTextF.getStyleClass().add("doctor-details-text-field");
        infoTextA.getStyleClass().add("doctor-details-text-field");

        dataColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        sbpColumn.setCellValueFactory(new PropertyValueFactory<>("sbp"));
        dbpColumn.setCellValueFactory(new PropertyValueFactory<>("dbp"));

        // Imposto uno stile per le varie soglie di valori
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

        // Data di oggi
        date = LocalDate.now();
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        String formattedDate = String.format("%02d/%02d/%04d", day, month, year);

        todayDate.setText(formattedDate);
    }
}


