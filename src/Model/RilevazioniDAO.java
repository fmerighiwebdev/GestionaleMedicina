package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RilevazioniDAO {

    private RilevazioniFactory rilevazioniFactory;

    public RilevazioniDAO() {
        this.rilevazioniFactory = new DefaultRilevazioniFactory();
    }

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
        }
    }

    public void updateRilevazione(Rilevazioni rilevazioni) {
        Connection conn = null;
        PreparedStatement stat = null;

        try {
            conn = DBManager.getConnection();

            String query = "UPDATE Rilevazioni SET SBP = ?, DBP = ?, Day = ?, Month = ?, " +
                    "Year = ?, Hours = ? WHERE IDPaziente = ? AND Day = ? AND Month = ? AND Year = ?";

            stat = conn.prepareStatement(query);
            stat.setInt(1, rilevazioni.getSbp());
            stat.setInt(2, rilevazioni.getDbp());
            stat.setInt(3, rilevazioni.getDay());
            stat.setInt(4, rilevazioni.getMonth());
            stat.setInt(5, rilevazioni.getYear());
            stat.setInt(6, rilevazioni.getHours());
            stat.setInt(7, rilevazioni.getIdPaziente());
            stat.setInt(8, rilevazioni.getDay());
            stat.setInt(9, rilevazioni.getMonth());
            stat.setInt(10, rilevazioni.getYear());

            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean rilevazioniExist(int idPaziente, int day, int month, int year) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();

            String query = "SELECT COUNT(*) FROM Rilevazioni WHERE IDPaziente = ? AND Day = ? AND Month = ? AND Year = ?";
            stat = conn.prepareStatement(query);
            stat.setInt(1, idPaziente);
            stat.setInt(2, day);
            stat.setInt(3, month);
            stat.setInt(4, year);

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

    public List<Rilevazioni> getRilevazioneByPazienteID(int pazienteId) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Rilevazioni> rilevazioniList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            String query = "SELECT * FROM Rilevazioni WHERE IDPaziente = ?";
            stat = conn.prepareStatement(query);
            stat.setInt(1, pazienteId);
            rs = stat.executeQuery();

            while (rs.next()) {
                int sbp = rs.getInt("SBP");
                int dbp = rs.getInt("DBP");
                int day = rs.getInt("Day");
                int month = rs.getInt("Month");
                int year = rs.getInt("Year");
                int hours = rs.getInt("Hours");
                int idPaziente = rs.getInt("IDPaziente");
                Rilevazioni rilevazioni = rilevazioniFactory.createRilevazione(sbp, dbp, day, month, year, hours, idPaziente);
                rilevazioniList.add(rilevazioni);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rilevazioniList;
    }

}
