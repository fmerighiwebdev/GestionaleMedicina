package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE = "log.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static Logger instance;

    private PrintWriter writer;

    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter(LOG_FILE, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Singleton
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void logMedicoInserimentoTerapia(String paziente, String medico) {
        String message = String.format("[%s] Inserita TERAPIA per paziente '%s' da parte del medico '%s'%n",
                getCurrentDateTime(), paziente, medico);
        writeLog(message);
    }

    public void logPazienteInserimentoRilevazione(String paziente) {
        String message = String.format("[%s] Inserita RILEVAZIONE da parte di '%s'%n",
                getCurrentDateTime(), paziente);
        writeLog(message);
    }

    private void writeLog(String message) {
        if (writer != null) {
            writer.write(message);
            writer.flush();
        }
    }

    private String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}