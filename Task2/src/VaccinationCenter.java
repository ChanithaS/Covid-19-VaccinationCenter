import java.io.*;
import java.util.Scanner;

public class VaccinationCenter {
    private static Scanner sc = new Scanner(System.in);
    public static Booth[] booth = new Booth[6];
    static int emptyBooth = 0;
    private static int classBoothNum;
    private static int noOfVaccines = 150;

    /**
     * initialize booth and prints menu
     * @param args
     */
    public static void main(String[] args)
    {
        //first initializing the booth and printing the menu
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
            booth[x] = new Booth(".");
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
        //checking no of booths empty and shows messages directly without going into the function
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
                ClassAddPatient();
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
                System.out.println("|                         input not valid                          |");
                System.out.println("|                       Enter a valid input:                       |");
                //if an invalid input is given asking user for a valid input and recurse the function again till a valid input is given
                String validInput = sc.next();
                classMenuChoiceValidator(validInput);
        }
    }

    //    --------------------------------------------Menu Functions------------------------------------------------------

    /**
     * prints booth values
     */
    private static void ClassViewBooth() {
        //gets the length of the booth and prints the empty booths and names of patients
        for(int i = 0; i < booth.length; i++)
        {
            if (booth[i].getPatientName().equals("."))
            {

                System.out.println("|                         booth " + i + " is empty                         |");
            }
            else
            {
                System.out.println("|            booth is occupied by " + booth[i].getPatientName());
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
            if (booth[i].getPatientName().equals("."))
            {
                System.out.println("|                         booth " + i + " is empty                         |");
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        ClassMenuManager();
    }

    /**
     * adding patient to booth
     */
    private static void ClassAddPatient()
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
            boolean valid = false;
            while (!valid)
            {
                //value of boothNum will be passed by calling the function intValidator and does the function according to the value
                ClassIntValidator("|              Enter booth number (0-5) or 6 to stop:              |\n|                            : ");

                if (classBoothNum == 6)
                {
                    ClassMenu();
                    valid = true;
                }
                else if(classBoothNum==0||classBoothNum==1||classBoothNum==2||classBoothNum==3||classBoothNum==4||classBoothNum==5)
                {
                    //here it will first check is there is a patient in the booth by checking . is present
                    if(booth[classBoothNum].getPatientName().equals("."))
                    {
                        System.out.println("|                Enter customer name for booth " + classBoothNum + " :                 |");
                        System.out.print("|                            : ");
                        //getting an string of customers name and making its first character to a uppercase character
                        // which will be use full is sorting names in alphabetical order as ASCII values are compared
                        String customerName = sc.next();
                        customerName = customerName.substring(0,1).toUpperCase() + customerName.substring(1);
                        //setting the customer name according to the number to names array
                        booth[classBoothNum].setPatientName(customerName);
                        //decreasing 1 from total no of vaccines present
                        noOfVaccines -= 1;
                        System.out.println("|          Customer "+customerName+" was added to Booth No. "+classBoothNum);
                        //checking if booth is full if so telling the user ..if not continuing.
                        ClassEmptyBoothChecker();
                        if (emptyBooth == 0)
                        {
                            System.out.println("|                     All booths are occupied                      |");
                            ClassMenu();
                            //ending the while loop and returning to menu
                            valid = true;
                        }
                    }
                    else
                    {
                        System.out.println("|                   "+classBoothNum + " is occupied by a patient                    |");
                        ClassAddPatient();
                        //ending the while loop and returning to menu if not the loop will run throughout the program and give errors
                        valid = true;
                    }
                }
                else {
                    System.out.println("|                         input not valid                          |");
                }
            }
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

            if (classBoothNum == 6)
            {
                ClassMenu();
                valid1 = true;
            }
            else if(classBoothNum==0||classBoothNum==1||classBoothNum==2||classBoothNum==3||classBoothNum==4||classBoothNum==5)
            {
                //checking if a patient is present and if not will only remove the patient
                //respective booth number is passen here
                if(booth[classBoothNum].getPatientName().equals("."))
                {
                    System.out.println("|                "+classBoothNum + " doesnt have a patient to remove                 |");
                }
                else
                {
                    //replacing the customers name with the default . symbol which shows an empty booth
                    System.out.println("|        Customer "+booth[classBoothNum].getPatientName()+" was deleted from Booth No. "+classBoothNum);
                    booth[classBoothNum].setPatientName(".");
                    ClassEmptyBoothChecker();
                    if (emptyBooth == 6)
                    {
                        System.out.println("|                       All booths are Empty                       |");
                        ClassMenu();
                        //ending the while loop
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
            for (int j = i + 1; j < booth.length; j++) {                                                                //for every i index a loop of j values from 1 to 6 is run
                if (booth[i].getPatientName().compareTo(booth[j].getPatientName()) > 0) {                               //the two values are compared by ASCII value each time therefore only if index i value is larger the functions will be called
                    temp = booth[i].getPatientName();                                                                   //the value related to index i is stored in a temporary string
                    booth[i].setPatientName(booth[j].getPatientName());                                                 //the value related to index i is replaced with index j patient name
                    booth[j].setPatientName(temp);                                                                      //and j index value is replaced with temporary value
                }
            }
        }
        System.out.println("|              The names in alphabetical order are:                |");
        for (int i = 0; i < booth.length; i++) {
            if(booth[i].getPatientName() != ".")
            {
                System.out.println(booth[i].getPatientName());
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
            //creating a new data file and storing data into it as an object output stream
            //and values are written into the file
            ObjectOutputStream SaveFile = new ObjectOutputStream(new FileOutputStream("D1ata.txt"));    //referred from https://www.programiz.com/java-programming/objectoutputstream
            SaveFile.writeInt(noOfVaccines);                                                                 //referred from https://stackoverflow.com/questions/27787067/storing-integers-and-arrays-in-a-file-and-reading-them

            SaveFile.writeObject(booth);
            //closing the save file after data is saved
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
            ObjectInputStream LoadFile = new ObjectInputStream(new FileInputStream("D1ata.txt"));
            //data is then passed into the preferred variables
            noOfVaccines = LoadFile.readInt();
            booth = (Booth[]) LoadFile.readObject();
            LoadFile.close();
            ClassMenu();
        } catch (IOException | ClassNotFoundException e) {
            //if any saved files cannot be found giving a error massage
            System.out.println("|       You don't have any files to Load. Please Save a file       |");
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
                //while loop will stop after the addNo is greater than needToAdd number
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
            if (booth[i].getPatientName().equals("."))
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
        System.out.print(test);
        while (!sc.hasNextInt()) {
            //this will check for integers and update the classBoothNum
            System.out.println("|                     Please enter a number                        |");
            System.out.print("|                            : ");
            sc.next();
        }
        classBoothNum = sc.nextInt();
    }
}
