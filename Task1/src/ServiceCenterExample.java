import java.util.Scanner;

public class ServiceCenterExample
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String customerName;
        int tokenNum;
        String agentName;
        int boothNum = 0;
        String[] ServiceCenter = new String[7];
        //for (int x = 0; x < 6; x++ ) hotel[x] = ""; //initialise
        initialise(ServiceCenter); //better to initialise in a procedure
        while (boothNum < 6)
        {
            for (int x = 0; x < 6; x++)
            {
                if (ServiceCenter[x].equals("e")) System.out.println("booth " + x + " is empty");
            }
            System.out.println("Enter booth number (0-5) or 6 to stop:");
            boothNum = input.nextInt();
            System.out.println("Enter customer name for booth " + boothNum + " :");
            customerName = input.next();
            ServiceCenter[boothNum] = customerName;
            for (int x = 0; x < 6; x++)
            {
                System.out.println("booth " + x + " occupied by " +
                        ServiceCenter[x]);
            }
        }
    }

    private static void initialise(String hotelRef[])
    {
        for (int x = 0; x < 6; x++)
            hotelRef[x] = "e";
        System.out.println("initilise ");
    }
}
