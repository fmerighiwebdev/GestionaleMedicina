package Model;

// Classe "modello" dottore
// Inizializza i campi condivisi nei controller tramite getter e setter
public class Medico {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;

    // Costruttore
    public Medico(int id, String username, String password, String name, String surname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname() {
        return surname;
    }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

}
