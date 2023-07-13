package Model;

public interface PazienteFactory {
    Paziente createPaziente(int id, String username, String password, String name, String surname,
                                String symptoms, String medicine, int assumptions, int quantity,
                                String info, int medicoAss);
}
