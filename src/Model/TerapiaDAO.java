package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TerapiaDAO {

    public void insertTerapia(Terapia terapia) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = DBManager.getConnection();

            String query = "INSERT INTO Terapia (MedicineTherapy, AssumptionsTherapy, QuantityTherapy, " +
                    "IndicationsTherapy, IDPaziente) VALUES (?, ?, ?, ?, ?)";

            stat = conn.prepareStatement(query);
            stat.setString(1, terapia.getMedicineTherapy());
            stat.setInt(2, terapia.getAssTherapy());
            stat.setInt(3, terapia.getQuantityTherapy());
            stat.setString(4, terapia.getIndTheray());
            stat.setInt(5, terapia.getIdPaziente());

            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTerapia(Terapia terapia) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = DBManager.getConnection();

            String query = "UPDATE Terapia SET MedicineTherapy = ?, AssumptionsTherapy = ?, " +
                    "QuantityTherapy = ?, IndicationsTherapy = ?, IDPaziente = ? WHERE IDPaziente = ?";

            stat = conn.prepareStatement(query);
            stat.setString(1, terapia.getMedicineTherapy());
            stat.setInt(2, terapia.getAssTherapy());
            stat.setInt(3, terapia.getQuantityTherapy());
            stat.setString(4, terapia.getIndTheray());
            stat.setInt(5, terapia.getIdPaziente());
            stat.setInt(6, terapia.getIdPaziente());

            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean terapiaExist(int idPaziente) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();

            String query = "SELECT COUNT(*) FROM Terapia WHERE IDPaziente = ?";
            stat = conn.prepareStatement(query);
            stat.setInt(1, idPaziente);

            rs = stat.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
