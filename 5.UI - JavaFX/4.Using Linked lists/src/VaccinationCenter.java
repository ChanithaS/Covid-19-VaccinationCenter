import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class VaccinationCenter {
    private static Scanner sc = new Scanner(System.in);
    public static Booth[] booth = new Booth[6];
    static int emptyBooth = 0;
    private static int validatorNo;
    private static int noOfVaccines = 150;

    private static LinkedList listAstraZeneca = new LinkedList();
    private static LinkedList listSinopharm = new LinkedList();
    private static LinkedList listPfizer = new LinkedList();

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
//        checks for empty booths
        ClassEmptyBoothChecker();
        //cases are activated according users choice
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
        //prints out patient names and empty booths in the booth array
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
        //checks is any patient is in the waiting list and prints them
        if (listAstraZeneca.size() > 0 || listSinopharm.size() > 0||listPfizer.size() > 0)
        {
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|                     Patients in waiting list                     |");
            for (int i = 0; i < listAstraZeneca.size(); i++)
            {
                Object[] waitingInfoGet = (Object[]) listAstraZeneca.get(i);
                System.out.println(Arrays.toString(waitingInfoGet));
            }
            for (int i = 0; i < listSinopharm.size(); i++)
            {
                Object[] waitingInfoGet1 = (Object[]) listSinopharm.get(i);
                System.out.println(Arrays.toString(waitingInfoGet1));
            }
            for (int i = 0; i < listPfizer.size(); i++)
            {
                Object[] waitingInfoGet2 = (Object[]) listPfizer.get(i);
                System.out.println(Arrays.toString(waitingInfoGet2));
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
                    if(booth[0].getFirstName().equals(".") || booth[1].getFirstName().equals("."))
                    {
                        //gives the respective booth numbers which AstraZeneca patients will add to getNo function
                        getNo(0,1, "AstraZeneca");
                    }
                    else
                    {
                        //if respective booths are full the patient will add into the waiting list
                        //value 7 is passed into AddingInfo to activate the function
                        System.out.println("|            Adding patient to AstraZeneca waiting list            |");
                        AddingInfo(7);
                    }
                }
                //Adds patient to Sinopharm booth
                else if(validatorNo == 2)
                {
                    if(booth[2].getFirstName().equals(".") || booth[3].getFirstName().equals("."))
                    {
                        //gives the respective booth numbers which Sinopharm patients will add to getNo function
                        getNo(2,3, "Sinopharm");
                    }
                    else
                    {
                        //if respective booths are full the patient will add into the waiting list
                        //value 8 is passed into AddingInfo to activate the function
                        System.out.println("|             Adding patient to Sinopharm waiting list             |");
                        AddingInfo(8);
                    }
                }
                //Adds patient to Pfizer booth
                else if(validatorNo == 3)
                {
                    if(booth[4].getFirstName().equals(".") || booth[5].getFirstName().equals("."))
                    {
                        //gives the respective booth numbers which Pfizer patients will add to getNo function
                        getNo(4,5, "Pfizer");
                    }
                    else
                    {
                        //if respective booths are full the patient will add into the waiting list
                        //value 9 is passed into AddingInfo to activate the function
                        System.out.println("|              Adding patient to Pfizer waiting list               |");
                        AddingInfo(9);
                    }
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
            //respective vaccination type is set
            booth[first].setVaccineType(vacType);
        }
        else if(booth[second].getFirstName().equals("."))
        {
            AddingInfo(second);
            booth[second].setVaccineType(vacType);
        }
    }

    /**
     * gets input and assign to array/linked list
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

        //GETTING THE SURNAME OF CUSTOMER
        System.out.println("|                     Enter Your Surname :                         |");
        System.out.print("|                            : ");
        String customerSurname = sc.next();
        customerSurname = customerSurname.substring(0,1).toUpperCase() + customerSurname.substring(1);                  //getting an string of customers name and making its first character to a uppercase character

        //GETTING THE CITY OF CUSTOMER
        System.out.println("|                     Enter Your City :                            |");
        System.out.print("|                            : ");
        String cityName = sc.next();

        //GETTING THE AGE OF CUSTOMER
        int ageValue = 0;
        ClassIntValidator("|                     Enter Your age :                             |\n|                            : ");
        boolean validAge = false;
        while(!validAge)
        {
            //getting a valid input from user so in between 100 to 18
            if (validatorNo < 100 && validatorNo > 18)
            {
                ageValue = validatorNo;
                validAge = true;
            }
            else{
                //checking if user is actually in range and if not letting the user exit the program
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
                    else if(yesOrNo.equals("Q") || yesOrNo.equals("q"))
                    {
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

        //GETTING THE NIC NUMBER OF CUSTOMER
        System.out.println("|                     Enter Your NIC/Passport number :             |");
        System.out.print("|                            : ");
        String nicOrPassport;
        nicOrPassport = sc.next();
        boolean validNic = false;
        while(!validNic)
        {
            //validating if the card number entered is in between 9-10 letters
            if (nicOrPassport.length() == 9 || nicOrPassport.length() == 10)
            {
                validNic = true;
            }
            else{
                System.out.println("|   Not a valid NIC/Passport number, Please enter a valid number   |");
                System.out.print("|                            : ");
                nicOrPassport = sc.next();
            }
        }

        //SETTING DETAILS OBTAINED TO BOOTH ARRAY OR TO WAITING LIST
        //creating a new object array and assigning values from user input
        Object[] waitingInfo = {customerFirstName, customerSurname, ageValue, cityName, nicOrPassport};
        //function carried out according to the value passed
        if(boothNo == 7)
        {
            //the object array is passed into the linked list of AstraZeneca
            listAstraZeneca.add(waitingInfo);
        }
        else if(boothNo == 8)
        {
            //the object array is passed into the linked list of Sinopharm
            listSinopharm.add(waitingInfo);
        }
        else if(boothNo == 9)
        {
            //the object array is passed into the linked list of Pfizer
            listPfizer.add(waitingInfo);
        }
        else{
            //values are set into the booth array if booth is free
            booth[boothNo].setFirstName(customerFirstName);                                                                 //setting the customer name according to the number to names array
            booth[boothNo].setSurName(customerSurname);                                                                     //setting the customer name according to the number to names array
            booth[boothNo].setCity(cityName);                                                                               //setting the customer name according to the number to names array
            booth[boothNo].setAge(ageValue);
            booth[boothNo].setNIC(nicOrPassport);

            //decreasing 1 from total no of vaccines present
            noOfVaccines -= 1;
            System.out.println("|          Customer "+customerFirstName+ " "+ customerSurname+" was added to Booth No. "+boothNo);
            ClassEmptyBoothChecker();
            if (emptyBooth == 0)
            {
                //telling user that booths are full and next patient will add to waiting list
                System.out.println("|                     Initiating waiting list                      |");
            }
        }
    }

    /**
     * remove patient and ADD linked list patient
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
                //exiting the function
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
                    //replacing the customers name and other values with the default symbols which shows an empty booth
                    System.out.println("|        Customer "+booth[validatorNo].getFirstName()+" "+ booth[validatorNo].getSurName() +" was deleted from Booth No. "+ validatorNo);
                    booth[validatorNo].setFirstName(".");
                    booth[validatorNo].setSurName(".");
                    booth[validatorNo].setAge(0);
                    booth[validatorNo].setCity(".");
                    booth[validatorNo].setNIC(".");
                    booth[validatorNo].setVaccineType(".");

                    //if any patients are present in waiting list, the patient will get add up into the removed booth number
                    //adding according to vaccine type
                    if ((validatorNo == 0 || validatorNo == 1) && listAstraZeneca.size() != 0)
                    {
                        System.out.println("|        Assigning waiting list patient to booth "+ validatorNo);
                        //assigning AstraZeneca waiting list first element patient into a Object array
                        Object[] waitingInfoGet = (Object[]) listAstraZeneca.get(0);
                        //the value from the Object array is taken and set into the respective place in the booth array
                        booth[validatorNo].setFirstName((String) waitingInfoGet[0]);
                        booth[validatorNo].setSurName((String) waitingInfoGet[1]);

                        booth[validatorNo].setAge((Integer) waitingInfoGet[2]);
                        booth[validatorNo].setCity((String) waitingInfoGet[3]);
                        booth[validatorNo].setNIC((String) waitingInfoGet[4]);
                        booth[validatorNo].setVaccineType("AstraZeneca");

                        //then the taken object is removed from the linked list
                        listAstraZeneca.removeFirst();
                        //decreasing 1 from total no of vaccines present
                        noOfVaccines -= 1;
                    }
                    else if ((validatorNo == 2 || validatorNo == 3) && listSinopharm.size() != 0)
                    {
                        System.out.println("|        Assigning waiting list patient to booth "+ validatorNo);
                        //assigning Sinopharm waiting list first element patient into a Object array
                        Object[] waitingInfoGet = (Object[]) listSinopharm.get(0);
                        //the value from the Object array is taken and set into the respective place in the booth array
                        booth[validatorNo].setFirstName((String) waitingInfoGet[0]);
                        booth[validatorNo].setSurName((String) waitingInfoGet[1]);
                        booth[validatorNo].setAge((int) waitingInfoGet[2]);
                        booth[validatorNo].setCity((String) waitingInfoGet[3]);
                        booth[validatorNo].setNIC((String) waitingInfoGet[4]);
                        booth[validatorNo].setVaccineType("Sinopharm");

                        //then the taken object is removed from the linked list
                        listSinopharm.removeFirst();
                        //decreasing 1 from total no of vaccines present
                        noOfVaccines -= 1;
                    }
                    else if ((validatorNo == 4 || validatorNo == 5) && listPfizer.size() != 0)
                    {
                        System.out.println("|        Assigning waiting list patient to booth "+ validatorNo);
                        //assigning Pfizer waiting list first element patient into a Object array
                        Object[] waitingInfoGet = (Object[]) listPfizer.get(0);
                        //the value from the Object array is taken and set into the respective place in the booth array
                        booth[validatorNo].setFirstName((String) waitingInfoGet[0]);
                        booth[validatorNo].setSurName((String) waitingInfoGet[1]);
                        booth[validatorNo].setAge((int) waitingInfoGet[2]);
                        booth[validatorNo].setCity((String) waitingInfoGet[3]);
                        booth[validatorNo].setNIC((String) waitingInfoGet[4]);
                        booth[validatorNo].setVaccineType("Pfizer");

                        //then the taken object is removed from the linked list
                        listPfizer.removeFirst();
                        //decreasing 1 from total no of vaccines present
                        noOfVaccines -= 1;
                    }

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
        for(int i = 0; i < booth.length; i++ ) {
            for (int j = i + 1; j < booth.length; j++) {
                if (booth[i].getFirstName().compareTo(booth[j].getFirstName()) > 0) {
                    temp = booth[i].getFirstName();
                    booth[i].setFirstName(booth[j].getFirstName());
                    booth[j].setFirstName(temp);
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
            //creating a new data file and storing data into it
            ObjectOutputStream SaveFile = new ObjectOutputStream(new FileOutputStream("ClassData.txt"));    //referred from https://www.programiz.com/java-programming/objectoutputstream
            SaveFile.writeInt(noOfVaccines);                                                                     //referred from https://stackoverflow.com/questions/27787067/storing-integers-and-arrays-in-a-file-and-reading-them
            SaveFile.writeObject(booth);
            SaveFile.writeObject(listAstraZeneca);
            SaveFile.writeObject(listPfizer);
            SaveFile.writeObject(listSinopharm);
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
            //loading data from the saved file
            ObjectInputStream LoadFile = new ObjectInputStream(new FileInputStream("ClassData.txt"));
            noOfVaccines = LoadFile.readInt();
            booth = (Booth[]) LoadFile.readObject();
            listAstraZeneca = (LinkedList) LoadFile.readObject();
            listSinopharm = (LinkedList) LoadFile.readObject();
            listPfizer = (LinkedList) LoadFile.readObject();
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
