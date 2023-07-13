package Model;

public class DefaultTerapiaFactory implements TerapiaFactory {
    public Terapia createTerapia(String medicineTherapy, int assTherapy, int quantityTherapy, String indTherapy,
                                 int idPaziente) {
        return new Terapia(medicineTherapy, assTherapy, quantityTherapy, indTherapy, idPaziente);
    }
}
