package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Classe Data Access Object
// Si occupa di recuperare i dati dal DB - tabella Medico (con username)
public class MedicoDAO {

    public Medico getDottoreByUsername(String username) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Medico medico = null;

        try {
            conn = DBManager.getConnection();

            String query = "SELECT * FROM Medico WHERE Username = ?";
            stat = conn.prepareStatement(query);
            stat.setString(1, username);
            rs = stat.executeQuery();

            if (rs.next()) {
                String password = rs.getString("Password");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                medico = new Medico(username, password, name, surname);
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

        return medico;
    }

}
