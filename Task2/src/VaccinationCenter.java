import java.util.Scanner;

public class VaccinationCenter {
    private static Scanner sc = new Scanner(System.in);
    private static Booth[] booth = new Booth[6];
    static int emptyBooth = 0;
    private static int classBoothNum;
    private static int noOfVaccines = 150;

    public static void main(String[] args)
    {
        ClassInitialise();
        ClassMenu();
    }
    private static void ClassInitialise()
    {
        //initializing the array by putting a default value of . to the array
        for (int x = 0; x < 6; x++)
            booth[x] = new Booth(".");
    }

//    --------------------------------------------Menu------------------------------------------------------

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
            System.out.println("Thank you for using the system");
            break;
            default:
                System.out.println("Input not valid");
                System.out.println("Enter a valid input: ");
                String validInput = sc.next();
                classMenuChoiceValidator(validInput);
        }
    }

    //    --------------------------------------------Menu Functions------------------------------------------------------

    private static void ClassViewBooth() {
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
                            valid = true;
                        }
                    }
                    else
                    {
                        System.out.println("|                   "+classBoothNum + " is occupied by a patient                    |");
                        ClassAddPatient();
                        valid = true;
                    }
                }
                else {
                    System.out.println("|                         input not valid                          |");
                }
            }
        }
    }
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
                        valid1 = true;
                    }
                }
            }
            else {
                System.out.println("|                         input not valid                          |");
            }
        }
    }
    private static void ClassPatientsSorted()
    {
        System.out.println("|------------------------------------------------------------------|");                     //https://www.geeksforgeeks.org/java-program-to-sort-names-in-an-alphabetical-order/
        String temp;
        for(int i = 0; i < booth.length; i++ ) {
            for (int j = i + 1; j < booth.length; j++) {
                if (booth[i].getPatientName().compareTo(booth[j].getPatientName()) > 0) {
                    temp = booth[i].getPatientName();
                    booth[i].setPatientName(booth[j].getPatientName());
                    booth[j].setPatientName(temp);
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
    private static void ClassSave() {
    }
    private static void ClassLoad() {
    }
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
                ClassMenu();
            }
        }
        System.out.println("|------------------------------------------------------------------|");
    }

    //    --------------------------------------------validation------------------------------------------------------

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
    public static void ClassIntValidator(String test)
    {
        //used to find if user enters numbers rather than Strings or numbers out of range
        do{
            System.out.print(test);
            while (!sc.hasNextInt()) {
                //this will check for integers
                System.out.println("|                     Please enter a number                        |");
                System.out.print("|                            : ");
                sc.next();
            }
            classBoothNum = sc.nextInt();
        } while (classBoothNum > 6);
    }
}
