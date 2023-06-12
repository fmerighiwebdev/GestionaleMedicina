package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RilevazioniDAO {

    public void insertRilevazione(Rilevazioni rilevazioni) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = DBManager.getConnection();

            String query = "INSERT INTO Rilevazioni (SBP, DBP, Day, Month, Year, Hours, IDPaziente) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            stat = conn.prepareStatement(query);
            stat.setInt(1, rilevazioni.getSbp());
            stat.setInt(2, rilevazioni.getDbp());
            stat.setInt(3, rilevazioni.getDay());
            stat.setInt(4, rilevazioni.getMonth());
            stat.setInt(5, rilevazioni.getYear());
            stat.setInt(6, rilevazioni.getHours());
            stat.setInt(7, rilevazioni.getIdPaziente());

            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudi le risorse
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
