package Model;

public interface Factory {
    Medico createMedico(int id, String username, String password, String name, String surname, String email);
    Paziente createPaziente(int id, String username, String password, String name, String surname,
                            String symptoms, String medicine, int assumptions, int quantity,
                            String info, int medicoAss);
    Rilevazioni createRilevazione(int sbp, int dbp, int day, int month, int year, int hours, int idPaziente);
    Terapia createTerapia(String medicineTherapy, int assTherapy, int quantityTherapy, String indTherapy,
                          int idPaziente);
}
