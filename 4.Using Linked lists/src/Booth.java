import java.io.Serializable;
import java.util.LinkedList;

public class Booth extends Patient implements Serializable {
    private String vaccineType;

    /**
     * constructor for booth class with variables if patient class extended
     * @param firstName
     * @param surName
     * @param age
     * @param city
     * @param NIC
     * @param vaccineType
     */
    //as Booth class extends patients class the variables in patient class also can be accessed here by using a super
    public Booth(String firstName, String surName, int age, String city, String NIC, String vaccineType) {
        super(firstName, surName, age, city, NIC);
        this.vaccineType = vaccineType;
    }

    /**
     *print all info stored
     */
    public void printInfo()
    {
        super.printPatientInfo();
        System.out.println("Vaccine Type : " + vaccineType);
    }

    //gets and sets values accordingly
    public String getVaccineType() {
        return vaccineType;
    }

    /**
     * set vaccine type
     * @param vaccineType
     */
    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }
}
