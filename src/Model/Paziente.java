package Model;

// Classe "modello" paziente
// Inizializza i campi condivisi nei controller tramite getter e setter
public class Paziente {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String symptoms;
    private String medicine;
    private int assumptions;
    private int quantity;
    private String info;

    // Costruttore
    public Paziente(String username, String password, String name, String surname,
                        String symptoms, String medicine, int assumptions, int quantity) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.symptoms = symptoms;
        this.medicine = medicine;
        this.assumptions = assumptions;
        this.quantity = quantity;
    }

    // Getters and setters
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
}
