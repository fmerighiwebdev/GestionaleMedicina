package Model;

public class DefaultMedicoFactory implements MedicoFactory {
    public Medico createMedico(int id, String username, String password, String name,
                               String surname, String email) {
        return new Medico(id, username, password, name, surname, email);
    }
}
