package Model;

public interface RilevazioniFactory {
    Rilevazioni createRilevazione(int sbp, int dbp, int day, int month, int year, int hours, int idPaziente);
}
