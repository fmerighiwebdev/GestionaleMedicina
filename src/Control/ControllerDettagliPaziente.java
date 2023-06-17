package Control;


import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

public class ControllerDettagliPaziente {
    @FXML
    private TextField title;
    @FXML
    private Button logoutButton;
    @FXML
    private Button sendButton;
    @FXML
    private TextField faketextfield1;
    @FXML
    private TextField faketextfield2;
    @FXML
    private TextField faketextfield3;
    @FXML
    private TextField faketextfield4;
    @FXML
    private TextArea symptomsTextA;
    @FXML
    private TextField medicineTextF;
    @FXML
    private TextField assumptionsTextF;
    @FXML
    private TextField quantityTextF;
    @FXML
    private TextField sbpTextF;
    @FXML
    private TextField dbpTextF;
    @FXML
    private TextField dayTextF;
    @FXML
    private TextField monthTextF;
    @FXML
    private TextField yearTextF;
    @FXML
    private TextField hoursTextF;

    @FXML
    private Label medicineThLabel;
    @FXML
    private Label assThLabel;
    @FXML
    private Label quantThLabel;
    @FXML
    private Label indThLabel;

    @FXML
    private Label nomeMedico;
    @FXML
    private Label emailMedico;

    @FXML
    private Label fullName;
    @FXML
    private Label todayDate;
    private String username;
    private LocalDate date;

    // Setto l'username preso dal controller di login paziente
    public void setUsername(String username) {
        this.username = username;
    }

    // Evento collegato al bottone "Logout" in PazienteDettagli.fxml
    @FXML
    private void bottoneLogout(ActionEvent event) {
        try {
            // Carica il template precedente PazienteLogin.fxml
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

    // Evento collegato al bottone "Invia" in PazienteDettagli.fxml
    @FXML
    private void sendRilevations(ActionEvent event) {
        // Prendo i dati dagli input
        String symptoms = symptomsTextA.getText();
        String medicine = medicineTextF.getText();
        String ass = assumptionsTextF.getText();
        String quantity = quantityTextF.getText();

        String sbp = sbpTextF.getText();
        String dbp = dbpTextF.getText();
        String day = dayTextF.getText();
        String month = monthTextF.getText();
        String year = yearTextF.getText();
        String hours = hoursTextF.getText();

        // Prendo i dati della terapia assegnata (per controllo su coerenza dati)
        String medicineTherapy = medicineThLabel.getText();
        int assTherapy = Integer.parseInt(assThLabel.getText());
        int quantityTherapy = Integer.parseInt(quantThLabel.getText());

        // Controllo sui valori - campo vuoto
        if (symptoms.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci i sintomi rilevati durante la terapia");
            isEmptyAlert.showAndWait();
            return;
        } else if (medicine.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci il farmaco assunto");
            isEmptyAlert.showAndWait();
            return;
        } else if (ass.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci il numero di assunzioni del farmaco");
            isEmptyAlert.showAndWait();
            return;
        } else if (quantity.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci la quantità di farmaco assunta");
            isEmptyAlert.showAndWait();
            return;
        } else if (sbp.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci un valore per SBP");
            isEmptyAlert.showAndWait();
            return;
        } else if (dbp.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci un valore per DBP");
            isEmptyAlert.showAndWait();
            return;
        } else if (day.isEmpty() || month.isEmpty() || year.isEmpty() || hours.isEmpty()) {
            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("Errore in input");
            isEmptyAlert.setHeaderText(null);
            isEmptyAlert.setContentText("Inserisci la data e/o l'ora");
            isEmptyAlert.showAndWait();
            return;
        }

        // Controllo sui valori - valore errato
        try {
            int assIntVal = Integer.parseInt(ass);
            int quantityIntVal = Integer.parseInt(quantity);
        } catch (NumberFormatException eNumber) {
            Alert isWrongAlert = new Alert(Alert.AlertType.ERROR);
            isWrongAlert.setTitle("Errore in input");
            isWrongAlert.setHeaderText(null);
            isWrongAlert.setContentText("Inserisci un valore numerico valido per N°assunzioni e/o quantità");
            isWrongAlert.showAndWait();
            return;
        }
        try {
            int sbpIntVal = Integer.parseInt(sbp);
            int dbpIntVal = Integer.parseInt(dbp);
        } catch (NumberFormatException eNumber) {
            Alert isWrongAlert = new Alert(Alert.AlertType.ERROR);
            isWrongAlert.setTitle("Errore in input");
            isWrongAlert.setHeaderText(null);
            isWrongAlert.setContentText("Inserisci un valore numerico valido per SBP e/o DBP");
            isWrongAlert.showAndWait();
            return;
        }
        try {
            int dayIntVal = Integer.parseInt(day);
            int monthIntVal = Integer.parseInt(month);
            int yearIntVal = Integer.parseInt(year);
            int hoursIntVal = Integer.parseInt(hours);

            LocalDate inputDate = LocalDate.of(yearIntVal, monthIntVal, dayIntVal);
            date = LocalDate.now();

            if (inputDate.isAfter(date)) {
                Alert isWrongAlert = new Alert(Alert.AlertType.ERROR);
                isWrongAlert.setTitle("Errore in input");
                isWrongAlert.setHeaderText(null);
                isWrongAlert.setContentText("Non puoi inserire un giorno successivo alla data odierna");
                isWrongAlert.showAndWait();
                return;
            }

            if (hoursIntVal < 0 || hoursIntVal > 24) {
                Alert isWrongAlert = new Alert(Alert.AlertType.ERROR);
                isWrongAlert.setTitle("Errore in input");
                isWrongAlert.setHeaderText(null);
                isWrongAlert.setContentText("Ora non valida");
                isWrongAlert.showAndWait();
                return;
            }
        } catch (NumberFormatException eNumber) {
            Alert isWrongAlert = new Alert(Alert.AlertType.ERROR);
            isWrongAlert.setTitle("Errore in input");
            isWrongAlert.setHeaderText(null);
            isWrongAlert.setContentText("Inserisci un valore numerico valido per data e/o ora");
            isWrongAlert.showAndWait();
            return;
        }

        // Controllo sui valori - coerenza dati inviati
        if (!medicine.equals(medicineTherapy)) {
            Alert isWrongAlert = new Alert(Alert.AlertType.ERROR);
            isWrongAlert.setTitle("Errore in input");
            isWrongAlert.setHeaderText(null);
            isWrongAlert.setContentText("Il farmaco inserito non è coerente con quello assegnato nella terapia");
            isWrongAlert.showAndWait();
            return;
        } else if (Integer.parseInt(ass) != assTherapy) {
            Alert isWrongAlert = new Alert(Alert.AlertType.ERROR);
            isWrongAlert.setTitle("Errore in input");
            isWrongAlert.setHeaderText(null);
            isWrongAlert.setContentText("Il numero di assunzioni inserito non è coerente con quello assegnato nella terapia");
            isWrongAlert.showAndWait();
            return;
        } else if (Integer.parseInt(quantity) != quantityTherapy) {
            Alert isWrongAlert = new Alert(Alert.AlertType.ERROR);
            isWrongAlert.setTitle("Errore in input");
            isWrongAlert.setHeaderText(null);
            isWrongAlert.setContentText("La quantità inserita non è coerente con quella assegnata nella terapia");
            isWrongAlert.showAndWait();
        }

        // Creo il nuovo Access Data Object e l'oggetto Paziente
        PazienteDAO pazienteDAO = new PazienteDAO();
        Paziente paziente = pazienteDAO.getPazienteByUsername(username);

        // Creo il nuovo Access Data Object e l'oggetto Rilevazioni
        RilevazioniDAO rilevazioniDAO = new RilevazioniDAO();
        int sbpInt = Integer.parseInt(sbp);
        int dbpInt = Integer.parseInt(dbp);
        int dayInt = Integer.parseInt(day);
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);
        int hoursInt = Integer.parseInt(hours);
        Rilevazioni rilevazioni = new Rilevazioni(sbpInt, dbpInt, dayInt, monthInt, yearInt, hoursInt, paziente.getId());

        // Setto tramite i setter della classe modello i valori presi in input
        paziente.setSymptoms(symptoms);
        paziente.setMedicine(medicine);
        paziente.setAssumptions(Integer.parseInt(ass));
        paziente.setQuantity(Integer.parseInt(quantity));

        // Controllo se esistono già delle rilevazioni effettuate in data odierna
        boolean rilevazioniEsistenti = rilevazioniDAO.rilevazioniExist(paziente.getId(), dayInt, monthInt, yearInt);

        if (rilevazioniEsistenti) {
            // Aggiorna la tabella se era già presente una voce in data odierna
            rilevazioniDAO.updateRilevazione(rilevazioni);
        } else {
            // Inserisci una rilevazione alla tabella se non presente in data odierna
            rilevazioniDAO.insertRilevazione(rilevazioni);
        }

        // Invio eseguito
        Alert sendSuccessfull = new Alert(Alert.AlertType.CONFIRMATION);
        sendSuccessfull.setTitle("Invio completato");
        sendSuccessfull.setHeaderText(null);
        sendSuccessfull.setContentText("I dati sono stati inviati correttamente al database");
        sendSuccessfull.showAndWait();
    }

    // Setting proprietà statiche
    @FXML
    public void initialize() {
        // CSS Class
        title.getStyleClass().add("title");
        logoutButton.getStyleClass().add("logout-button");
        sendButton.getStyleClass().add("send-button");

        faketextfield1.getStyleClass().add("faketextfield1");
        faketextfield2.getStyleClass().add("faketextfield2");
        faketextfield3.getStyleClass().add("faketextfield3");
        faketextfield4.getStyleClass().add("faketextfield4");

        symptomsTextA.getStyleClass().add("patient-details-text-area");
        medicineTextF.getStyleClass().add("patient-details-text-field");
        assumptionsTextF.getStyleClass().add("patient-details-text-field");
        quantityTextF.getStyleClass().add("patient-details-text-field");
        sbpTextF.getStyleClass().add("patient-details-text-field");
        dbpTextF.getStyleClass().add("patient-details-text-field");
        dayTextF.getStyleClass().add("patient-details-text-field");
        monthTextF.getStyleClass().add("patient-details-text-field");
        yearTextF.getStyleClass().add("patient-details-text-field");
        hoursTextF.getStyleClass().add("patient-details-text-field");
        todayDate.getStyleClass().add("today-date");

        medicineThLabel.getStyleClass().add("medicine-th-label");
        assThLabel.getStyleClass().add("ass-th-label");
        quantThLabel.getStyleClass().add("quant-th-label");
        indThLabel.getStyleClass().add("ind-th-label");

        // Name and surname in label
        // Uso l'username settato per recuperare i dati dalla tabella grazie al modello creato
        PazienteDAO pazienteDAO = new PazienteDAO();
        Paziente paziente = pazienteDAO.getPazienteByUsername(username);

        if (paziente != null) {
            String name = paziente.getName();
            String surname = paziente.getSurname();
            fullName.setText(name + " " + surname);

            Terapia terapia = pazienteDAO.getTerapiaByPazienteID(paziente.getId());

            if (terapia != null) {
                String medTh = terapia.getMedicineTherapy();
                int assTh = terapia.getAssTherapy();
                int quantTh = terapia.getQuantityTherapy();
                String indTh = terapia.getIndTherapy();

                medicineThLabel.setText(medTh);
                assThLabel.setText(String.valueOf(assTh));
                quantThLabel.setText(String.valueOf(quantTh));
                indThLabel.setText(indTh);
            }

            Medico medico = pazienteDAO.getMedicoByMedicoAss(paziente.getMedicoAss());

            if (medico != null) {
                String nameDoc = medico.getName();
                String surnameDoc = medico.getSurname();
                String emailDoc = medico.getEmail();

                nomeMedico.setText(nameDoc + " " + surnameDoc);
                emailMedico.setText(emailDoc);
            }
        }

        // Data di oggi
        date = LocalDate.now();
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        String formattedDate = String.format("%02d/%02d/%04d", day, month, year);

        todayDate.setText(formattedDate);
    }

}
