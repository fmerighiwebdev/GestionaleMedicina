package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Classe Data Access Object
// Si occupa di recuperare i dati dal DB - tabella Paziente (con username)
public class PazienteDAO {

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
                String password = rs.getString("Password");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                paziente = new Paziente(username, password, name, surname);
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

}
