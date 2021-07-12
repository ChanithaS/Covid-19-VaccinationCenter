public class Booth {
    private static String patientName;

    public Booth(String patientName)
    {
        this.patientName = patientName;
    }
    public static String getPatientName()
    {
        return patientName;
    }
    public void setPatientName(String newName)
    {
        this.patientName = newName;
    }
}
