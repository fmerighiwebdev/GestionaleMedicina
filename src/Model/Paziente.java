package Model;

// Classe "modello" paziente
// Inizializza i campi condivisi nei controller tramite getter e setter
public class Paziente {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String symptoms;
    private String medicine;
    private int assumptions;
    private int quantity;
    private String info;
    private int medicoAss;

    // Costruttore
    public Paziente(int id, String username, String password, String name, String surname,
                        String symptoms, String medicine, int assumptions, int quantity, String info, int medicoAss) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.symptoms = symptoms;
        this.medicine = medicine;
        this.assumptions = assumptions;
        this.quantity = quantity;
        this.info = info;
        this.medicoAss = medicoAss;
    }

    // Getters and setters
    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() { return username; }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() { return password; }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() { return name; }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname() {
        return surname;
    }

    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getSymptoms() { return symptoms; }

    public void setMedicine(String medicine) { this.medicine = medicine; }
    public String getMedicine() { return medicine; }

    public void setAssumptions(int assumptions) { this.assumptions = assumptions; }
    public int getAssumptions() { return assumptions; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getQuantity() { return quantity; }

    public void setInfo(String info) { this.info = info; }
    public String getInfo() { return info; }

    public void setMedicoAss(int medicoAss) { this.medicoAss = medicoAss; }
    public int getMedicoAss() { return medicoAss; }
}
