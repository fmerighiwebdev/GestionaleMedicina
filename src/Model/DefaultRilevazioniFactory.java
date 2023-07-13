package Model;

public class DefaultRilevazioniFactory implements RilevazioniFactory {
    public Rilevazioni createRilevazione(int sbp, int dbp, int day, int month, int year, int hours, int idPaziente) {
        return new Rilevazioni(sbp, dbp, day, month, year, hours, idPaziente);
    }
}
