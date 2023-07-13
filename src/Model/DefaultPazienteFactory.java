package Model;

public class DefaultPazienteFactory implements PazienteFactory {
    public Paziente createPaziente(int id, String username, String password, String name, String surname,
                                   String symptoms, String medicine, int assumptions, int quantity,
                                   String info, int medicoAss) {
        return new Paziente(id, username, password, name, surname, symptoms, medicine, assumptions,
                            quantity, info, medicoAss);
    }
}
