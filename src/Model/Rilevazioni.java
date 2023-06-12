package Model;

public class Rilevazioni {

    private int sbp;
    private int dbp;
    private int day;
    private int month;
    private int year;
    private int hours;
    private int idPaziente;

    public Rilevazioni(int sbp, int dbp, int day, int month, int year, int hours, int idPaziente) {
        this.sbp = sbp;
        this.dbp = dbp;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.idPaziente = idPaziente;
    }

    public void setSbp(int sbp) { this.sbp = sbp; }
    public int getSbp() { return sbp; }

    public void setDbp(int dbp) { this.dbp = dbp; }
    public int getDbp() { return dbp; }

    public void setDay(int day) { this.day = day; }
    public int getDay() { return day; }

    public void setMonth(int month) { this.month = month; }
    public int getMonth() { return month; }

    public void setYear(int year) { this.year = year; }
    public int getYear() { return year; }

    public void setHours(int hours) { this.hours = hours; }
    public int getHours() { return hours; }

    public void setIdPaziente(int idPaziente) { this.idPaziente = idPaziente; }
    public int getIdPaziente() { return idPaziente; }

}
