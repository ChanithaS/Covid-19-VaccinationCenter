import java.io.Serializable;

public class Booth implements Serializable {
    private String patientName;

    /**
     * constructor for booth class patient name
     * @param patientName
     */
    public Booth(String patientName) {
        this.patientName = patientName;
    }

    /**
     * return patient name
     * @return
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * set patient name
     * @param patientName
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
