import java.io.*;
import java.util.Scanner;

public class Task1Ext {

    private static Scanner sc = new Scanner(System.in);
    public static String[] firstNames = new String[6];
    public static String[] surNames = new String[6];
    private static int noOfVaccines = 150;
    private static int VaccinationNo;
    static int EmptyBooth = 0;

    public static void main(String[] args)
    {
        initialise();
        Menu();
    }
    public static void ViewBooth()
    {
        //printing out the string value in names array and if . present telling booth is empty
        System.out.println("|------------------------------------------------------------------|");
        for(int i = 0; i < firstNames.length; i++)
        {
            if (firstNames[i].equals("."))
            {
                System.out.println("|                         booth " + i + " is empty                         |");
            }
            else
            {
                System.out.println("|            booth is occupied by " + firstNames[i] + surNames[i]);
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        MenuManager();
    }
    public static void ViewEmptyBooth()
    {
        //if names array contain . printing out an empty booth is present
        System.out.println("|------------------------------------------------------------------|");
        for(int i = 0; i < firstNames.length; i++)
        {
            if (firstNames[i].equals("."))
            {
                System.out.println("|                         booth " + i + " is empty                         |");
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        MenuManager();
    }
    public static void AddPatient(boolean valid)
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
            while (!valid)
            {
                //value of VaccinationNo will be passed by calling the function intValidator and does the function according to the value
                intValidator("|                           1.AstraZeneca                          |\n" +
                        "|                           2.Sinopharm                            |\n|                           3.Pfizer                               |\n" +
                        "|enter the respective number of the vaccination you want or 6 to exit|\n|                            : ");

                if (VaccinationNo == 6)
                {
                    //exits the adding patient function
                    Menu();
                    valid = true;
                }
                //Adds patient to AstraZeneca booth
                else if(VaccinationNo == 1)
                {
                    //gives the respective booth numbers which AstraZeneca patients will add to getNo function
                    getNo(0,1);
                }
                //Adds patient to Sinopharm booth
                else if(VaccinationNo == 2)
                {
                    //gives the respective booth numbers which Sinopharm patients will add to getNo function
                    getNo(2,3);
                }
                //Adds patient to Pfizer booth
                else if(VaccinationNo == 3)
                {
                    //gives the respective booth numbers which Pfizer patients will add to getNo function
                    getNo(4,5);
                }
                else {
                    System.out.println("|                         input not valid                          |");
                }
            }
        }
    }
    public static void getNo(int first, int second)
    {
        //Here it'll check if the passed booth numbers are empty/ if not calling the AddingInfo function with respective value
        //the same respective value is passed to AddingInfo function
        if(firstNames[first].equals("."))
        {
            AddingInfo(first);
        }
        else if(firstNames[second].equals("."))
        {
            AddingInfo(second);
        }
        //if all booths are occupied, returning true
        else {
            System.out.println("|         Booths for AstraZeneca is occupied by a patients         |");
            AddPatient(true);
        }
    }
    public static void AddingInfo(int boothNo)
    {
        //Getting information according to the respective value
        System.out.println("|                     Enter Your First Name :                      |");
        System.out.print("|                            : ");
        //getting an string of customers name and making its first character to a uppercase character
        // which will be use full is sorting names in alphabetical order as ASCII values are compared
        String customerFirstName = sc.next();
        customerFirstName = customerFirstName.substring(0,1).toUpperCase() + customerFirstName.substring(1);
        //setting the customer name according to the number to names array
        firstNames[boothNo] = customerFirstName;

        System.out.println("|                     Enter Your Surname :                         |");
        System.out.print("|                            : ");
        //getting an string of customers name and making its first character to a uppercase character
        // which will be use full is sorting names in alphabetical order as ASCII values are compared
        String Surname = sc.next();
        Surname = Surname.substring(0,1).toUpperCase() + Surname.substring(1);
        //setting the customer name according to the number to names array
        surNames[boothNo] = Surname;

        //decreasing 1 from total no of vaccines present
        noOfVaccines -= 1;
        System.out.println("|          Customer "+customerFirstName+" "+ Surname+" was added to Booth No. "+boothNo);
        EmptyBoothChecker();
        if (EmptyBooth == 0)
        {
            System.out.println("|                     All booths are occupied                      |");
            Menu();
            AddPatient(true);
        }
    }

    public static void RemovePatient()
    {
        System.out.println("|------------------------------------------------------------------|");
        boolean valid1 = false;
        while (!valid1)
        {
            //running a while loop till boolean valid1 is true
            //value of boothNum will be passed by calling the function intValidator and does the function according to the value
            intValidator("|     Enter booth number (0-5) to remove patient or 6 to stop:     |\n|                            : ");

            if (VaccinationNo == 6)
            {
                Menu();
                valid1 = true;
            }
            else if(VaccinationNo==0||VaccinationNo==1||VaccinationNo==2||VaccinationNo==3||VaccinationNo==4||VaccinationNo==5)
            {
                //checking if a patient is present and if not will only remove the patient
                if(firstNames[VaccinationNo].equals("."))
                {
                    System.out.println("|                "+VaccinationNo + " doesnt have a patient to remove                 |");
                }
                else
                {
                    //replacing the customers name with the default . symbol which shows an empty booth
                    System.out.println("|        Customer "+ firstNames[VaccinationNo]+" "+ firstNames[VaccinationNo]+" was deleted from Booth No. "+VaccinationNo);
                    firstNames[VaccinationNo] = ".";
                    surNames[VaccinationNo] = ".";
                    EmptyBoothChecker();
                    if (EmptyBooth == 6)
                    {
                        System.out.println("|                       All booths are Empty                       |");
                        Menu();
                        valid1 = true;
                    }
                }
            }
            else {
                System.out.println("|                         input not valid                          |");
            }
        }
    }
    public static void PatientsSorted()
    {
        System.out.println("|------------------------------------------------------------------|");                     //https://www.geeksforgeeks.org/java-program-to-sort-names-in-an-alphabetical-order/
        String first;
        for(int i = 0; i < firstNames.length; i++ ) {
            for (int j = i + 1; j < firstNames.length; j++) {
                if (firstNames[i].compareTo(firstNames[j]) > 0) {
                    first = firstNames[i];
                    firstNames[i] = firstNames[j];
                    firstNames[j] = first;
                }
                if (surNames[i].compareTo(surNames[j]) > 0) {
                    first = surNames[i];
                    surNames[i] = surNames[j];
                    surNames[j] = first;
                }
            }
        }
        System.out.println("|              The names in alphabetical order are:                |");
        for (int i = 0; i < firstNames.length; i++) {
            if(firstNames[i] != ".")
            {
                System.out.println(firstNames[i] + surNames[i]);
            }
        }
        System.out.println("|------------------------------------------------------------------|");
        MenuManager();
    }
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
            } while (addNo > needToAdd);


            if(addNo > needToAdd)
            {
                System.out.println("|                      Can't add more than "+needToAdd +"                      |");
                System.out.println("|------------------------------------------------------------------|");
            }
            else
            {
                noOfVaccines += addNo;
                System.out.println("|                      Added "+ addNo+ " to the stock                       |");
                System.out.println("|                     Total no of vaccines "+ noOfVaccines+"                     |");
                Menu();
            }
        }
        System.out.println("|------------------------------------------------------------------|");
    }
    public static void Save()
    {
        try {
            //creating a new data file and storing data into it
            ObjectOutputStream SaveFile = new ObjectOutputStream(new FileOutputStream("Data.txt"));    //referred from https://www.programiz.com/java-programming/objectoutputstream
            SaveFile.writeInt(noOfVaccines);                                                                 //referred from https://stackoverflow.com/questions/27787067/storing-integers-and-arrays-in-a-file-and-reading-them
            SaveFile.writeObject(firstNames);
            SaveFile.writeObject(surNames);
            SaveFile.close();
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|                          Data Saved                              |");
            System.out.println("|------------------------------------------------------------------|");
            Menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Load()
    {
        System.out.println("|------------------------------------------------------------------|");
        try {
            System.out.println("|                      Loading saved data....                      |");
            //loading data from the saved file
            ObjectInputStream LoadFile = new ObjectInputStream(new FileInputStream("Data.txt"));
            noOfVaccines = LoadFile.readInt();
            firstNames = (String[]) LoadFile.readObject();
            surNames = (String[]) LoadFile.readObject();
            LoadFile.close();
            Menu();
        } catch (IOException | ClassNotFoundException e) {
            //if any saved files cannot be found giving a error massage
            System.out.println("|       You don't have any files to Load. Please Save a file       |");
            Menu();
        }
        System.out.println("|------------------------------------------------------------------|");
    }

    //********************************************** Validation ******************************************************

    public static void intValidator(String test)
    {
        //used to find if user enters numbers rather than Strings or numbers out of range
        System.out.print(test);
        while (!sc.hasNextInt()) {
            //this will check for integers
            System.out.println("|                     Please enter a number                        |");
            System.out.print("|                            : ");
            sc.next();
        }
        VaccinationNo = sc.nextInt();
    }
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
    private static void initialise()
    {
        //initializing the array by putting a default value of . to the array
        for (int x = 0; x < 6; x++)
        {
            firstNames[x] = ".";
            surNames[x]=".";
        }

    }
    public static void EmptyBoothChecker()
    {
        //getting no of empty boots in the array wich can be used for validation
        EmptyBooth = 0;

        for(int i = 0; i < firstNames.length; i++)
        {
            if (firstNames[i].equals("."))
            {
                EmptyBooth += 1;
            }
        }
    }

    //********************************************** Menu ******************************************************

    public static void menuChoiceValidator(String userChoice)
    {
        //using a switch case statement with a string to validate input of user for the menu
        //the EmptyBoothChecker will get the value of EmptyBooths can call the function according to the value
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
                    AddPatient(false);
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
