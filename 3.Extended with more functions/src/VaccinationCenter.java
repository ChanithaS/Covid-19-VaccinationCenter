import java.io.*;
import java.util.Scanner;

public class VaccinationCenter {
    private static Scanner sc = new Scanner(System.in);
    public static Booth[] booth = new Booth[6];
    static int emptyBooth = 0;
    private static int validatorNo;
    private static int noOfVaccines = 150;

    /**
     * initialize booth and prints menu
     * @param args
     */
    public static void main(String[] args)
    {
        ClassInitialise();
        ClassMenu();
    }

    /**
     * initialize booth class
     */
    private static void ClassInitialise()
    {
        //initializing the array by putting a default value of . to the array
        for (int x = 0; x < 6; x++)
            booth[x] = new Booth(".",".",0,".",".",".");
    }

//    --------------------------------------------Menu------------------------------------------------------

    /**
     * prints menu
     */
    public static void ClassMenu()
    {
        //printing the menu
        System.out.println("|------------------------------------------------------------------|");
        System.out.println("|                COVID-19 VACCINATION CENTER                       |");
        System.out.println("|------------------------------------------------------------------|");
        //if vaccines are less than 20 printing a warning message
        if(noOfVaccines <= 20)
        {
            System.out.println("|                                                                  |");
            System.out.println("|                         **** Warning ****                        |");
            System.out.println("|                Only "+ noOfVaccines +" Vaccines remaining                     |");
            System.out.println("|------------------------------------------------------------------|");
        }
        System.out.println("|       100 or VVB: View all Vaccination Booths                    |");
        System.out.println("|       101 or VEB: View all Empty Booths                          |");
        System.out.println("|       102 or APB: Add Patient to a Booth                         |");
        System.out.println("|       103 or RPB: Remove Patient from a Booth                    |");
        System.out.println("|       104 or VPS: View Patients Sorted in alphabetical order     |");
        System.out.println("|       105 or SPD: Store Program Data into file                   |");
        System.out.println("|       106 or LPD: Load Program Data from file                    |");
        System.out.println("|       107 or VRV: View Remaining Vaccinations                    |");
        System.out.println("|       108 or AVS: Add Vaccinations to the Stock                  |");
        System.out.println("|       999 or EXT: Exit the Program                               |");
        System.out.println("|__________________________________________________________________|");
        System.out.print("|                            : ");
        //string value is taken from user and passed into menuChoiceValidator and respective functions are called
        String choice = sc.next();
        classMenuChoiceValidator(choice);
    }

    /**
     * validate menu input
     * @param userChoice
     */
    public static void classMenuChoiceValidator(String userChoice)
    {
        ClassEmptyBoothChecker();
        switch (userChoice)
        {
            case "100": case "VVB":
            ClassViewBooth();
            break;
            case "101": case "VEB":
            if (emptyBooth > 0)
            {
                ClassViewEmptyBooth();
            }
            else
            {
                System.out.println("All booths are occupied");
                ClassMenu();
            }

            break;
            case "102": case "APB":
            if (emptyBooth > 0)
            {
                ClassAddPatient(false);
            }
            else
            {
                System.out.println("All booths are occupied");
                ClassMenu();
            }
            break;
            case "103": case "RPB":
            if (emptyBooth == 6)
            {
                System.out.println("Booth is empty please add a patient");
                ClassMenu();
            }
            else
            {
                ClassRemovePatient();
            }
            break;
            case "104": case "VPS":
            if (emptyBooth==6)
            {
                System.out.println("Booth is empty please add a patient to sort out patients");
            }
            else
            {
                ClassPatientsSorted();
            }
            break;
            case "105": case "SPD":
            ClassSave();
            break;
            case "106": case "LPD":
            ClassLoad();
            break;
            case "107": case "VRV":
            ClassRemainingVaccines();
            break;
            case "108": case "AVS":
            ClassAddVaccines();
            break;
            case "999": case "EXT":
            System.out.println("|                  Thank you for using the system                  |");
            break;
            default:
                System.out.println("|              Input not valid enter a valid input:                |");
                System.out.print("|                            : ");
                String validInput = sc.next();
                classMenuChoiceValidator(validInput);
        }
    }

    //    --------------------------------------------Menu Functions------------------------------------------------------

    /**
     * prints booth values
     */
    private static void ClassViewBooth() {
        for(int i = 0; i < booth.length; i++)
        {
            if (booth[i].getFirstName().equals("."))
            {

                System.out.println("|                         booth " + i + " is empty                         |");
            }
            else
            {
                System.out.println("|                     Booth "+i+" is occupied by                       |");
                booth[i].printInfo();
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        ClassMenuManager();
    }

    /**
     * prints empty booths
     */
    private static void ClassViewEmptyBooth()
    {
        //if booth array contain . printing out an empty booth is present
        System.out.println("|------------------------------------------------------------------|");
        for(int i = 0; i < booth.length; i++)
        {
            if (booth[i].getFirstName().equals("."))
            {
                System.out.println("|                         booth " + i + " is empty                         |");
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        ClassMenuManager();
    }

    /**
     * adding patient to booth
     * @param valid
     */
    public static void ClassAddPatient(boolean valid)
    {
        //first will check if there is vaccines, if not no more patients cant be added
        if(noOfVaccines == 0)
        {
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|          Vaccines are over please add more to continue           |");
            System.out.println("|------------------------------------------------------------------|");
            ClassMenu();
        }

        else{
            //if there is vaccines the following functions will happen
            //the while loop will keep running a till a valid input is given by user
            while (!valid)
            {
                //value of VaccinationNo will be passed by calling the function intValidator and does the function according to the value
                ClassIntValidator("""
                        |                           1.AstraZeneca                          |
                        |                           2.Sinopharm                            |
                        |                           3.Pfizer                               |
                        |enter the respective number of the vaccination you want or 6 to exit|
                        |                            :\s""");

                if (validatorNo == 6)
                {
                    //exits the adding patient function
                    ClassMenu();
                    valid = true;
                }
                //Adds patient to AstraZeneca booth
                else if(validatorNo == 1)
                {
                    //gives the respective booth numbers which AstraZeneca patients will add to getNo function
                    getNo(0,1, "AstraZeneca");
                }
                //Adds patient to Sinopharm booth
                else if(validatorNo == 2)
                {
                    //gives the respective booth numbers which Sinopharm patients will add to getNo function
                    getNo(2,3, "Sinopharm");
                }
                //Adds patient to Pfizer booth
                else if(validatorNo == 3)
                {
                    //gives the respective booth numbers which Pfizer patients will add to getNo function
                    getNo(4,5, "Pfizer");
                }
                else {
                    System.out.println("|                         input not valid                          |");
                }
            }
        }
    }

    /**
     * gets value and passes accordingly
     * @param first
     * @param second
     * @param vacType
     */
    public static void getNo(int first, int second, String vacType)
    {
        //Here it'll check if the passed booth numbers are empty/ if not calling the AddingInfo function with respective value
        //the same respective value is passed to AddingInfo function
        if(booth[first].getFirstName().equals("."))
        {
            AddingInfo(first);
            booth[first].setVaccineType(vacType);
        }
        else if(booth[second].getFirstName().equals("."))
        {
            AddingInfo(second);
            booth[second].setVaccineType(vacType);
        }
        //if all booths are occupied, returning true
        else {
            System.out.println("|         Booths for AstraZeneca is occupied by a patients         |");
            ClassAddPatient(true);
        }
    }

    /**
     * gets input and assign to array
     * @param boothNo
     */
    public static void AddingInfo(int boothNo)
    {
        //Getting information according to the respective value
        //GETTING THE FIRST NAME OF CUSTOMER
        System.out.println("|                     Enter Your First Name :                      |");
        System.out.print("|                            : ");
        String customerFirstName = sc.next();                                                                           //getting an string of customers name and making its first character to a uppercase character
        customerFirstName = customerFirstName.substring(0,1).toUpperCase() + customerFirstName.substring(1);            // which will be use full is sorting names in alphabetical order as ASCII values are compared
        booth[boothNo].setFirstName(customerFirstName);                                                                 //setting the customer name according to the number to names array

        //GETTING THE SURNAME OF CUSTOMER
        System.out.println("|                     Enter Your Surname :                         |");
        System.out.print("|                            : ");
        String customerSurname = sc.next();
        customerSurname = customerSurname.substring(0,1).toUpperCase() + customerSurname.substring(1);                  //getting an string of customers name and making its first character to a uppercase character
        booth[boothNo].setSurName(customerSurname);                                                                     //setting the customer name according to the number to names array

        //GETTING THE AGE OF CUSTOMER
        ClassIntValidator("|                     Enter Your age :                             |\n|                            : ");
        boolean validAge = false;
        while(!validAge)
        {
            if (validatorNo < 100 && validatorNo > 18)
            {
                booth[boothNo].setAge(validatorNo);
                validAge = true;
            }
            else{
                System.out.println("|Vaccines are only available for people between 18 - 100 years old |");
                System.out.println("|     Enter Q/q if not in rage or C/c to enter a valid number      |");
                String yesOrNo;
                yesOrNo = sc.next();
                boolean validAnswer = false;
                while(!validAnswer)
                {
                    if (yesOrNo.equals("C") || yesOrNo.equals("c"))
                    {
                        validAnswer = true;
                    }
                    else if(yesOrNo.equals("Q") || yesOrNo.equals("q")){
                        booth[boothNo].setFirstName(".");
                        booth[boothNo].setSurName(".");
                        booth[boothNo].setAge(0);
                        ClassAddPatient(false);
                        validAnswer = true;
                        validAge = true;
                    }
                    else{
                        System.out.println("|     Enter Q/q if not in rage or C/c to enter a valid number      |");
                        System.out.print("|                            : ");
                        yesOrNo = sc.next();
                    }
                }
                ClassIntValidator("|                     Enter Your age :                             |\n|                            : ");

            }
        }

        //GETTING THE CITY OF CUSTOMER
        System.out.println("|                     Enter Your City :                            |");
        System.out.print("|                            : ");
        String cityName = sc.next();
        //setting the customer name according to the number to names array
        booth[boothNo].setCity(cityName);

        //GETTING THE NIC NUMBER OF CUSTOMER
        System.out.println("|                     Enter Your NIC/Passport number :             |");
        System.out.print("|                            : ");
        String nicOrPassport;
        nicOrPassport = sc.next();
        boolean validNic = false;
        while(!validNic)
        {
            if (nicOrPassport.length() == 9 || nicOrPassport.length() == 10)
            {
                booth[boothNo].setNIC(nicOrPassport);
                validNic = true;
            }
            else{
                System.out.println("|   Not a valid NIC/Passport number, Please enter a valid number   |");
                System.out.print("|                            : ");
                nicOrPassport = sc.next();
            }
        }

        //decreasing 1 from total no of vaccines present
        noOfVaccines -= 1;
        System.out.println("|          Customer "+customerFirstName+ " "+ customerSurname+" was added to Booth No. "+boothNo);
        ClassEmptyBoothChecker();
        if (emptyBooth == 0)
        {
            System.out.println("|                     All booths are occupied                      |");
            ClassMenu();
            ClassAddPatient(true);
        }
    }

    /**
     * remove patient from booth number
     */
    private static void ClassRemovePatient()
    {
        System.out.println("|------------------------------------------------------------------|");
        boolean valid1 = false;
        while (!valid1)
        {
            //running a while loop till boolean valid1 is true
            //value of boothNum will be passed by calling the function intValidator and does the function according to the value
            ClassIntValidator("|     Enter booth number (0-5) to remove patient or 6 to stop:     |\n|                            : ");

            if (validatorNo == 6)
            {
                ClassMenu();
                valid1 = true;
            }
            else if(validatorNo ==0|| validatorNo ==1|| validatorNo ==2|| validatorNo ==3|| validatorNo ==4|| validatorNo ==5)
            {
                //checking if a patient is present and if not will only remove the patient
                if(booth[validatorNo].getFirstName().equals("."))
                {
                    System.out.println("|                "+ validatorNo + " doesnt have a patient to remove                 |");
                }
                else
                {
                    //replacing the customers name with the default . symbol which shows an empty booth
                    System.out.println("|        Customer "+booth[validatorNo].getFirstName()+" "+ booth[validatorNo].getSurName() +" was deleted from Booth No. "+ validatorNo);
                    booth[validatorNo].setFirstName(".");
                    booth[validatorNo].setSurName(".");
                    booth[validatorNo].setAge(0);
                    booth[validatorNo].setCity(".");
                    booth[validatorNo].setNIC(".");
                    booth[validatorNo].setVaccineType(".");
                    ClassEmptyBoothChecker();
                    if (emptyBooth == 6)
                    {
                        System.out.println("|                       All booths are Empty                       |");
                        ClassMenu();
                        valid1 = true;
                    }
                }
            }
            else {
                System.out.println("|                         input not valid                          |");
            }
        }
    }

    /**
     * sort patients according to alphabetical order
     */
    private static void ClassPatientsSorted()
    {
        System.out.println("|------------------------------------------------------------------|");                     //https://www.geeksforgeeks.org/java-program-to-sort-names-in-an-alphabetical-order/
        String temp;
        int temp1;
        for(int i = 0; i < booth.length; i++ ) {                                                                        //for every i index a loop of j values from 1 to 6 is run
            for (int j = i + 1; j < booth.length; j++) {                                                                //the two values are compared by ASCII value each time therefore only if index i value is larger the functions will be called
                if (booth[i].getFirstName().compareTo(booth[j].getFirstName()) > 0) {                                   //the value related to index i is stored in a temporary string
                    temp = booth[i].getFirstName();                                                                     //the value related to index i is replaced with index j patient name
                    booth[i].setFirstName(booth[j].getFirstName());                                                     //and j index value is replaced with temporary value
                    booth[j].setFirstName(temp);

                    temp = booth[i].getSurName();                                                                     //the value related to index i is replaced with index j patient name
                    booth[i].setSurName(booth[j].getSurName());                                                     //and j index value is replaced with temporary value
                    booth[j].setSurName(temp);

                    temp = booth[i].getCity();                                                                     //the value related to index i is replaced with index j patient name
                    booth[i].setCity(booth[j].getCity());                                                     //and j index value is replaced with temporary value
                    booth[j].setCity(temp);

                    temp = booth[i].getNIC();                                                                     //the value related to index i is replaced with index j patient name
                    booth[i].setNIC(booth[j].getNIC());                                                     //and j index value is replaced with temporary value
                    booth[j].setNIC(temp);

                    temp = booth[i].getVaccineType();                                                                     //the value related to index i is replaced with index j patient name
                    booth[i].setVaccineType(booth[j].getVaccineType());                                                     //and j index value is replaced with temporary value
                    booth[j].setVaccineType(temp);

                    temp1 = booth[i].getAge();                                                                     //the value related to index i is replaced with index j patient name
                    booth[i].setAge(booth[j].getAge());                                                     //and j index value is replaced with temporary value
                    booth[j].setAge(temp1);
                }
            }
        }
        System.out.println("|              The names in alphabetical order are:                |");
        for (int i = 0; i < booth.length; i++) {
            if(booth[i].getFirstName() != ".")
            {
                System.out.println(booth[i].getFirstName() + " " + booth[i].getSurName());
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        ClassMenuManager();
    }

    /**
     * save data
     */
    private static void ClassSave()
    {
        try {
            //creating a new data file and storing data into it as a object output stream
            ObjectOutputStream SaveFile = new ObjectOutputStream(new FileOutputStream("ClassData.txt"));    //referred from https://www.programiz.com/java-programming/objectoutputstream
            SaveFile.writeInt(noOfVaccines);                                                                 //referred from https://stackoverflow.com/questions/27787067/storing-integers-and-arrays-in-a-file-and-reading-them
            SaveFile.writeObject(booth);
            //data is written to the file and closed
            SaveFile.close();
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|                          Data Saved                              |");
            System.out.println("|------------------------------------------------------------------|");
            ClassMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load data
     */
    private static void ClassLoad()
    {
        System.out.println("|------------------------------------------------------------------|");
        try {
            System.out.println("|                      Loading saved data....                      |");
            //loading data from the saved file as a new object output stream
            ObjectInputStream LoadFile = new ObjectInputStream(new FileInputStream("ClassData.txt"));
            //values are loaded and stored in the variables
            noOfVaccines = LoadFile.readInt();
            booth = (Booth[]) LoadFile.readObject();
            //closing the loaded file
            LoadFile.close();
            ClassMenu();
        } catch (IOException | ClassNotFoundException e) {
            //if any saved files cannot be found giving a error massage
            System.out.println("|       You don't have any files to Load. Please Save a file       |");
            e.printStackTrace();
            ClassMenu();
        }
        System.out.println("|------------------------------------------------------------------|");
    }

    /**
     * show remaining vaccine amount
     */
    private static void ClassRemainingVaccines()
    {
        //giving the no of vaccines present in different occasions
        System.out.println("|------------------------------------------------------------------|");
        if(noOfVaccines==0)
        {
            System.out.println("|                        Vaccines are over                         |");
            System.out.println("|------------------------------------------------------------------|");
            ClassMenuManager();
        }
        else if(noOfVaccines <= 20)
        {
            System.out.println("|                                                                  |");
            System.out.println("|                         **** Warning ****                        |");
            System.out.println("|                Only "+ noOfVaccines +" Vaccines remaining                     |");
            ClassMenu();
        }
        else
        {
            System.out.println("|                               "+noOfVaccines+"                                |");
            System.out.println("|------------------------------------------------------------------|");
            ClassMenuManager();
        }
    }

    /**
     * add vaccine
     */
    private static void ClassAddVaccines()
    {
        System.out.println("|------------------------------------------------------------------|");
        if(noOfVaccines == 150)
        {
            //returning to menu as vaccine stock is full
            System.out.println("|                Vaccines Stock Full cant add more                |");
            ClassMenu();
        }
        else
        {
            //letting user input a integer to add to noOfVaccines
            int addNo;
            int needToAdd;
            do {
                System.out.print("|            Enter the no.of vaccines to add to stock              |\n|                            : ");
                while (!sc.hasNextInt()) {
                    System.out.println("|                     Please enter a number                        |");
                    System.out.print("|                            : ");
                    sc.next();
                }
                addNo = sc.nextInt();
                //getting the used amount of vaccines and telling the user if he has input a value not within that range
                //also checking for integer validation
                needToAdd = 150 - noOfVaccines;
                if(addNo > needToAdd)
                {
                    System.out.println("|                      Can't add more than "+needToAdd +"                      |");
                }
            } while (addNo > needToAdd);
            noOfVaccines += addNo;
            System.out.println("|                      Added "+ addNo+ " to the stock                       |");
            System.out.println("|                     Total no of vaccines "+ noOfVaccines+"                     |");
            ClassMenu();
        }
        System.out.println("|------------------------------------------------------------------|");
    }

    //    --------------------------------------------validation------------------------------------------------------

    /**
     * check no of empty booths
     */
    private static void ClassEmptyBoothChecker()
    {
        //getting no of empty boots in the array which can be used for validation
        emptyBooth = 0;

        for(int i = 0; i < booth.length; i++)
        {
            if (booth[i].getFirstName().equals("."))
            {
                emptyBooth += 1;
            }
        }
    }

    /**
     * Asks to go back
     */
    public static void ClassMenuManager()
    {
        //get input if wants to go back till the user wants to read the outputs
        //using a while loop to check user input is valid
        boolean b = false;
        while(!b)
        {
            System.out.println("|                        Enter B to go back                        |");
            System.out.print("|                            : ");
            String in = sc.next();
            if (in.equals("B"))
            {
                ClassMenu();
                b = true;
            }
            else{
                System.out.println("|                         input not valid                          |");
            }
        }
        System.out.println("|------------------------------------------------------------------|");
    }

    /**
     * Validate until a integer is input
     * @param test
     */
    public static void ClassIntValidator(String test)
    {
        //used to find if user enters numbers rather than Strings or numbers out of range
        System.out.print(test);
        while (!sc.hasNextInt()) {
            //this will check for integers
            System.out.println("|                     Please enter a number                        |");
            System.out.print("|                            : ");
            sc.next();
        }
        validatorNo = sc.nextInt();
    }
}
