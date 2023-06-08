package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String URLDB = "jdbc:sqlite:GestionaleMedicinaDB";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URLDB);
                System.out.println("Connessione al DB stabilita");
            } catch (SQLException e) {
                System.out.println("Connessione fallita: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connessione al DB chiusa");
            } catch (SQLException e) {
                System.out.println("Errore durante la chiusura della connessione al DB: " + e.getMessage());
            }
        }
    }

}
