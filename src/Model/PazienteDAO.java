package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Classe Data Access Object
// Si occupa di gestire i dati del DB - tabella Paziente (con username)
public class PazienteDAO {

    // Prendo tutti i dati dal paziente con username = username
    public Paziente getPazienteByUsername(String username) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Paziente paziente = null;

        try {
            conn = DBManager.getConnection();

            String query = "SELECT * FROM Paziente WHERE Username = ?";
            stat = conn.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String symptoms = rs.getString("Symptoms");
                String medicine = rs.getString("Medicine");
                int ass = rs.getInt("Assumptions");
                int quantity = rs.getInt("Quantity");
                String info = rs.getString("Informations");
                paziente = new Paziente(id, username, password, name, surname, symptoms, medicine, ass, quantity, info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudi le risorse
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return paziente;
    }

    // Aggiorno la tabella paziente utilizzando la classe modello
    public void insertPaziente(Paziente paziente) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = DBManager.getConnection();

            String query = "UPDATE Paziente SET Symptoms = ?, Medicine = ?, Assumptions = ?, Quantity = ? " +
                    "WHERE Username = ?";
            stat = conn.prepareStatement(query);
            stat.setString(1, paziente.getSymptoms());
            stat.setString(2, paziente.getMedicine());
            stat.setInt(3, paziente.getAssumptions());
            stat.setInt(4, paziente.getQuantity());
            stat.setString(5, paziente.getUsername());

            stat.executeUpdate();
        } catch (SQLException eInsert) {
            eInsert.printStackTrace();
        }
    }

    public void updatePazienteInfo(String username, String info) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = DBManager.getConnection();

            String query = "UPDATE Paziente SET Informations = ? WHERE Username = ?";
            stat = conn.prepareStatement(query);
            stat.setString(1, info);
            stat.setString(2, username);

            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
