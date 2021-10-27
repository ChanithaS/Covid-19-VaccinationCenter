import java.io.Serializable;

public class Patient implements Serializable {
    private String firstName;
    private String surName;
    private int age;
    private String city;
    private String NIC;

    /**
     * constructor for patient class
     * @param firstName
     * @param surName
     * @param age
     * @param city
     * @param NIC
     */
    //constructor for patients class
    public Patient(String firstName, String surName, int age, String city, String NIC) {
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
        this.city = city;
        this.NIC = NIC;
    }

    /**
     * prints variable values
     */
    //Printing out values accordingly
    public void printPatientInfo() {
        System.out.println("Name         : "+ firstName +" "+ surName);
        System.out.println("Age          : " + age);
        System.out.println("City         : " + city);
        System.out.println("NIC          : " + NIC);
    }

    /**
     * return firstname
     * @return
     */
    //setting and getting values to the class
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * returns surname
     * @return
     */
    public String getSurName() {
        return surName;
    }

    /**
     * sets surname
     * @param surName
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * return age
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     * set age
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * return city
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * sets city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * return nic number
     * @return
     */
    public String getNIC() {
        return NIC;
    }

    /**
     * sets nic number
     * @param NIC
     */
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }
}
