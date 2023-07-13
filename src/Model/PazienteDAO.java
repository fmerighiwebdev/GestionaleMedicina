package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe Data Access Object
// Si occupa di gestire i dati del DB - tabella Paziente (con username)
public class PazienteDAO {

    // Factory
    private final Factory factory;

    public PazienteDAO() {
        this.factory = new DefaultFactory();
    }

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
                int medicoAss = rs.getInt("MedicoAss");
                paziente = factory.createPaziente(id, username, password, name, surname, symptoms, medicine, ass, quantity, info, medicoAss);
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

    public Terapia getTerapiaByPazienteID(int pazienteID) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Terapia terapia = null;

        try {
            conn = DBManager.getConnection();

            String query = "SELECT * FROM Terapia WHERE IDPaziente = ?";
            stat = conn.prepareStatement(query);
            stat.setInt(1, pazienteID);
            rs = stat.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID");
                String medTh = rs.getString("MedicineTherapy");
                int assTh = rs.getInt("AssumptionsTherapy");
                int quantTh = rs.getInt("QuantityTherapy");
                String indTh = rs.getString("IndicationsTherapy");
                terapia = factory.createTerapia(medTh, assTh, quantTh, indTh, pazienteID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return terapia;
    }

    public Medico getMedicoByMedicoAss(int medicoAss) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Medico medico = null;

        try {
            conn = DBManager.getConnection();

            String query = "SELECT * FROM Medico WHERE ID = ?";
            stat = conn.prepareStatement(query);
            stat.setInt(1, medicoAss);
            rs = stat.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                medico = factory.createMedico(id, username, password, name, surname, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medico;
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
                int id = rs.getInt("ID");
                int sbp = rs.getInt("SBP");
                int dbp = rs.getInt("DBP");
                int day = rs.getInt("Day");
                int month = rs.getInt("Month");
                int year = rs.getInt("Year");
                int hours = rs.getInt("Hours");
                int idPaziente = rs.getInt("IDPaziente");
                Rilevazioni rilevazioni = factory.createRilevazione(sbp, dbp, day, month, year, hours, idPaziente);
                rilevazioniList.add(rilevazioni);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rilevazioniList;
    }
}
