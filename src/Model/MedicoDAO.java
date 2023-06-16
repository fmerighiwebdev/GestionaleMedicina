package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String email = rs.getString("Email");
                medico = new Medico(id, username, password, name, surname, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medico;
    }

    public List<Paziente> getPazienti() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Paziente> pazienti = new ArrayList<>();

        try {
            conn = DBManager.getConnection();

            String query = "SELECT * FROM Paziente";
            stat = conn.prepareStatement(query);
            rs = stat.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String symptoms = rs.getString("Symptoms");
                String medicine = rs.getString("Medicine");
                int ass = rs.getInt("Assumptions");
                int quantity = rs.getInt("Quantity");
                String info = rs.getString("Informations");
                int medicoAss = rs.getInt("MedicoAss");
                Paziente paziente = new Paziente(id, username, password, name, surname, symptoms, medicine, ass, quantity, info, medicoAss);
                pazienti.add(paziente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pazienti;
    }


}
