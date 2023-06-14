package Model;

public class Terapia {

    private String medicineTherapy;
    private int assTherapy;
    private int quantityTherapy;
    private String indTheray;
    private int idPaziente;

    public Terapia(String medicineTherapy, int assTherapy, int quantityTherapy, String indTheray, int idPaziente) {
        this.medicineTherapy = medicineTherapy;
        this.assTherapy = assTherapy;
        this.quantityTherapy = quantityTherapy;
        this.indTheray = indTheray;
        this.idPaziente = idPaziente;
    }

    public void setMedicineTherapy(String medicineTherapy) { this.medicineTherapy = medicineTherapy; }
    public String getMedicineTherapy() { return medicineTherapy; }

    public void setAssTherapy(int assTherapy) { this.assTherapy = assTherapy; }
    public int getAssTherapy() { return assTherapy; }

    public void setQuantityTherapy(int quantityTherapy) { this.quantityTherapy = quantityTherapy; }
    public int getQuantityTherapy() { return quantityTherapy; }

    public String getIndTheray() { return indTheray; }

    public void setIndTheray(String indTheray) { this.indTheray = indTheray; }

    public void setIdPaziente(int idPaziente) { this.idPaziente = idPaziente; }
    public int getIdPaziente() { return idPaziente; }
}
