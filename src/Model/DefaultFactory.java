package Model;

public class DefaultFactory implements Factory {
    public Medico createMedico(int id, String username, String password, String name,
                               String surname, String email) {
        return new Medico(id, username, password, name, surname, email);
    }
    public Paziente createPaziente(int id, String username, String password, String name, String surname,
                                   String symptoms, String medicine, int assumptions, int quantity,
                                   String info, int medicoAss) {
        return new Paziente(id, username, password, name, surname, symptoms, medicine, assumptions,
                quantity, info, medicoAss);
    }

    public Rilevazioni createRilevazione(int sbp, int dbp, int day, int month, int year, int hours, int idPaziente) {
        return new Rilevazioni(sbp, dbp, day, month, year, hours, idPaziente);
    }

    public Terapia createTerapia(String medicineTherapy, int assTherapy, int quantityTherapy, String indTherapy,
                                 int idPaziente) {
        return new Terapia(medicineTherapy, assTherapy, quantityTherapy, indTherapy, idPaziente);
    }
}
