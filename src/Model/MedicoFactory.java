package Model;

public interface MedicoFactory {
    Medico createMedico(int id, String username, String password, String name, String surname, String email);
}
