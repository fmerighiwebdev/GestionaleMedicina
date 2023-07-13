package Model;

public interface TerapiaFactory {
    Terapia createTerapia(String medicineTherapy, int assTherapy, int quantityTherapy, String indTherapy,
                          int idPaziente);
}
