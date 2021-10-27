import java.io.*;
import java.util.Scanner;

public class Task1 {

    private static Scanner sc = new Scanner(System.in);
    public static String[] names = new String[6];
    private static int noOfVaccines = 150;
    private static int boothNum;
    static int EmptyBooth = 0;

    /**
     * initialize booth and prints menu
     * @param args
     */
    public static void main(String[] args)
    {
        initialise();
        Menu();
    }

    /**
     * prints booth values
     */
    public static void ViewBooth()
    {
        //printing out the string value in names array and if . present telling booth is empty
        System.out.println("|------------------------------------------------------------------|");
        for(int i = 0; i < names.length; i++)
        {
            if (names[i].equals("."))
            {
                System.out.println("|                         booth " + i + " is empty                         |");
            }
            else
            {
                System.out.println("|            booth is occupied by " + names[i]);
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        MenuManager();
    }

    /**
     * prints empty booths
     */
    public static void ViewEmptyBooth()
    {
        //if names array contain . printing out an empty booth is present
        System.out.println("|------------------------------------------------------------------|");
        for(int i = 0; i < names.length; i++)
        {
            if (names[i].equals("."))
            {
                System.out.println("|                         booth " + i + " is empty                         |");
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        MenuManager();
    }

    /**
     * adding patient to booth
     */
    public static void AddPatient()
    {
        //first will check if there is vaccines, if not no more patients cant be added
        if(noOfVaccines == 0)
        {
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|          Vaccines are over please add more to continue           |");
            System.out.println("|------------------------------------------------------------------|");
            Menu();
        }
        else{
            //if there is vaccines the following functions will happen
            //the while loop will keep running a till a valid input is given by user
            boolean valid = false;
            while (!valid)
            {
                //value of boothNum will be passed by calling the function intValidator and does the function according to the value
                intValidator("|              Enter booth number (0-5) or 6 to stop:              |\n|                            : ");

                if (boothNum == 6)
                {
                    Menu();
                    valid = true;
                }
                else if(boothNum==0||boothNum==1||boothNum==2||boothNum==3||boothNum==4||boothNum==5)
                {
                    //here it will first check is there is a patient in the booth by checking . is present
                    if(names[boothNum].equals("."))
                    {
                        System.out.println("|                Enter customer name for booth " + boothNum + " :                 |");
                        System.out.print("|                            : ");
                        //getting an string of customers name and making its first character to a uppercase character
                        // which will be useful is sorting names in alphabetical order as ASCII values are compared
                        String customerName = sc.next();
                        customerName = customerName.substring(0,1).toUpperCase() + customerName.substring(1);
                        //setting the customer name according to the number to names array
                        names[boothNum] = customerName;
                        //decreasing 1 from total no of vaccines present
                        noOfVaccines -= 1;
                        System.out.println("|          Customer "+customerName+" was added to Booth No. "+boothNum);
                        //checking if booth is full if so telling the user ..if not continuing.
                        EmptyBoothChecker();
                        if (EmptyBooth == 0)
                        {
                            System.out.println("|                     All booths are occupied                      |");
                            Menu();
                            //ending the while loop and returning to menu
                            valid = true;
                        }
                    }
                    else
                    {
                        System.out.println("|                   "+boothNum + " is occupied by a patient                    |");
                        AddPatient();
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
    public static void RemovePatient()
    {
        System.out.println("|------------------------------------------------------------------|");
        boolean valid1 = false;
        while (!valid1)
        {
            //running a while loop till boolean valid1 is true
            //value of boothNum will be passed by calling the function intValidator and does the function according to the value
            intValidator("|     Enter booth number (0-5) to remove patient or 6 to stop:     |\n|                            : ");

            if (boothNum == 6)
            {
                Menu();
                valid1 = true;
            }
            else if(boothNum==0||boothNum==1||boothNum==2||boothNum==3||boothNum==4||boothNum==5)
            {
                //checking if a patient is present and if not will only remove the patient
                if(names[boothNum].equals("."))
                {
                    System.out.println("|                "+boothNum + " doesnt have a patient to remove                 |");
                }
                else
                {
                    //replacing the customers name with the default . symbol which shows an empty booth
                    System.out.println("|        Customer "+names[boothNum]+" was deleted from Booth No. "+boothNum);
                    names[boothNum] = ".";
                    EmptyBoothChecker();
                    if (EmptyBooth == 6)
                    {
                        System.out.println("|                       All booths are Empty                       |");
                        Menu();
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
    public static void PatientsSorted()
    {
        System.out.println("|------------------------------------------------------------------|");                     //https://www.geeksforgeeks.org/java-program-to-sort-names-in-an-alphabetical-order/
        String temp;
        for(int i = 0; i < names.length; i++ ) {                                                                        //for every i index a loop of j values from 1 to 6 is run
            for (int j = i + 1; j < names.length; j++) {                                                                //the two values are compared each time by ASCII value therefore only if index i value is larger the functions will be called
                if (names[i].compareTo(names[j]) > 0) {                                                                 //the value related to index i is stored in a temporary string
                    temp = names[i];                                                                                    //the value related to index i is replaced with index j patient name
                    names[i] = names[j];                                                                                //and j index value is replaced with temporary value
                    names[j] = temp;
                }
            }
        }
        System.out.println("|              The names in alphabetical order are:                |");
        for (int i = 0; i < names.length; i++) {
            if(names[i] != ".")
            {
                System.out.println(names[i]);
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        MenuManager();
    }

    /**
     * show remaining vaccine amount
     */
    public static void RemainingVaccines()
    {
        //giving the no of vaccines present in different occasions
        System.out.println("|------------------------------------------------------------------|");
        if(noOfVaccines==0)
        {
            System.out.println("|                        Vaccines are over                         |");
            System.out.println("|------------------------------------------------------------------|");
            MenuManager();
        }
        else if(noOfVaccines <= 20)
        {
            System.out.println("|                                                                  |");
            System.out.println("|                         **** Warning ****                        |");
            System.out.println("|                Only "+ noOfVaccines +" Vaccines remaining                     |");
            Menu();
        }
        else
        {
            System.out.println("|                               "+noOfVaccines+"                                |");
            System.out.println("|------------------------------------------------------------------|");
            MenuManager();
        }
    }

    /**
     * add vaccine
     */
    public static void AddVaccines()
    {
        System.out.println("|------------------------------------------------------------------|");
        if(noOfVaccines == 150)
        {
            //returning to menu as vaccine stock is full
            System.out.println("|                Vaccines Stock Full cant add more                |");
            Menu();
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
            Menu();
        }
        System.out.println("|------------------------------------------------------------------|");
    }

    /**
     * save data
     */
    public static void Save()
    {
        try {
            //creating a new data file and storing data into it as an object output stream
            //and values are written into the file
            ObjectOutputStream SaveFile = new ObjectOutputStream(new FileOutputStream("Data.txt"));    //referred from https://www.programiz.com/java-programming/objectoutputstream
            SaveFile.writeInt(noOfVaccines);                                                                 //referred from https://stackoverflow.com/questions/27787067/storing-integers-and-arrays-in-a-file-and-reading-them
            SaveFile.writeObject(names);
            SaveFile.close();
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|                          Data Saved                              |");
            System.out.println("|------------------------------------------------------------------|");
            Menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load data
     */
    public static void Load()
    {
        System.out.println("|------------------------------------------------------------------|");
        try {
            System.out.println("|                      Loading saved data....                      |");
            //loading data from the saved file as a new object output stream
            ObjectInputStream LoadFile = new ObjectInputStream(new FileInputStream("Data.txt"));
            //data is then passed into the preferred variables
            noOfVaccines = LoadFile.readInt();
            names = (String[]) LoadFile.readObject();
            LoadFile.close();
            Menu();
        } catch (IOException | ClassNotFoundException e) {
            //if any saved files cannot be found giving a error massage
            System.out.println("|       You don't have any files to Load. Please Save a file       |");
            Menu();
        }
        System.out.println("|------------------------------------------------------------------|");
    }

    //**********************************************Validation******************************************************

    /**
     * Validate until a integer is input
     * @param test
     */
    public static void intValidator(String test)
    {
        //used to find if user enters numbers rather than Strings or numbers out of range
        do {
            System.out.print(test);
            while (!sc.hasNextInt()) {
                //this will check for integers
                System.out.println("|                     Please enter a number                        |");
                System.out.print("|                            : ");
                sc.next();
            }
            boothNum = sc.nextInt();
        } while (boothNum > 6);
    }

    /**
     * Asks to go back
     */
    public static void MenuManager()
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
                Menu();
                b = true;
            }
            else{
                System.out.println("|                         input not valid                          |");
            }
        }
        System.out.println("|------------------------------------------------------------------|");
    }

    /**
     * initialize booth class
     */
    private static void initialise()
    {
        //initializing the array by putting a default value of . to the array
        for (int x = 0; x < 6; x++)
            names[x] = ".";
    }

    /**
     * check no of empty booths
     */
    public static void EmptyBoothChecker()
    {
        //getting no of empty boots in the array which can be used for validation
        EmptyBooth = 0;

        for(int i = 0; i < names.length; i++)
        {
            if (names[i].equals("."))
            {
                EmptyBooth += 1;
            }
        }
    }

    //**********************************************Menu******************************************************

    /**
     * validate menu input
     * @param userChoice
     */
    public static void menuChoiceValidator(String userChoice)
    {
        //using a switch case statement with a string to validate input of user for the menu
        //the emptyBoothChecker will get the value of EmptyBooths can call the function according to the value
        EmptyBoothChecker();
        switch (userChoice)
        {
            case "100": case "VVB":
                ViewBooth();
                break;
            case "101": case "VEB":
                if (EmptyBooth > 0)
                {
                    ViewEmptyBooth();
                }
                else
                {
                    System.out.println("|                     All booths are occupied                      |");
                    Menu();
                }

                break;
            case "102": case "APB":
                if (EmptyBooth > 0)
                {
                    AddPatient();
                }
                else
                {
                    System.out.println("|                     All booths are occupied                      |");
                    Menu();
                }
                break;
            case "103": case "RPB":
                if (EmptyBooth == 6)
                {
                    System.out.println("|               Booth is empty please add a patient                |");
                    Menu();
                }
                else
                {
                    RemovePatient();
                }
                break;
            case "104": case "VPS":
                if (EmptyBooth==6)
                {
                    System.out.println("|     Booth is empty please add a patient to sort out patients     |");
                }
                else
                {
                    PatientsSorted();
                }
                break;
            case "105": case "SPD":
                Save();
                break;
            case "106": case "LPD":
                Load();
                break;
            case "107": case "VRV":
                RemainingVaccines();
                break;
            case "108": case "AVS":
                AddVaccines();
                break;
            case "999": case "EXT":
                System.out.println("|                Thank you for using the system                    |");
                break;
            default:
                System.out.println("|                         input not valid                          |");
                System.out.println("|                       Enter a valid input                        |");
                System.out.print("|                            : ");
                //if the input is invalid getting the user input again and passing it to the same function to loop
                String validInput = sc.next();
                menuChoiceValidator(validInput);
        }
    }

    /**
     * prints menu
     */
    public static void Menu()
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
        menuChoiceValidator(choice);
    }
}
