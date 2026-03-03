import java.util.Scanner;
interface Bookable
{
    void bookTicket();
    void cancelTicket();
    void editTickt(String newName,int newAge,String newDestination);
}
abstract class Ticket
{
    protected String passengerName;
    protected int age;
    protected String destination;
    protected boolean isCancelled=false;
    abstract void displayTicket();
}
class AirlineTicket extends Ticket implements Bookable
{
    Ticket tt;
    public AirlineTicket(String passengerName,int age,String destination)
    {
       if(passengerName.isEmpty()||passengerName.equals(null))
       {
           throw new InvalidPassengerException("Name can't be null or empty");
       }
       if(age<=0 || age>120)
       {
           throw new InvalidPassengerException("Age can't be less than 0 or greater than 120");
       }
       if(destination.isEmpty()||destination.equals(null))
       {
           throw new InvalidPassengerException("Destination can't be null or empty");
       }
        this.passengerName=passengerName;
        this.age=age;
        this.destination=destination;
    }
    public void bookTicket()
    {
        if(tt!=null)
        {
            throw new TicketAlreayExistsException("Already Active Ticket Exists");
        }
        else
        {
            System.out.println("Ticket booked");
            tt=(Ticket)tt;
        }
    }
    public void cancelTicket()
    {}
    public void editTickt(String newName,int newAge,String newDestination)
    {}
    public void displayTicket()
    {}
}
public class AirlineTicketBookingSystem
{
    static AirlineTicket ob=null;
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int x;
        do
        {
            System.out.println("Enter 1 to Book a Ticket");
            System.out.println("Enter 2 to Edit an exiting Ticket");
            System.out.println("Enter 3 to Cancel Ticket");
            System.out.println("Enter 4 to Display ticket details");
            System.out.println("Enter 5 to Exit the Application");
            System.out.print("Enter your choice:");
            x=sc.nextInt();
            switch(x)
            {
                case 1:
                    System.out.print("Enter your name:");
                    String n=sc.next();
                    System.out.print("Enter your age:");
                    int a=sc.nextInt();
                    System.out.print("Enter your Destination:");
                    String d=sc.next();
                    try
                    {
                        ob=new AirlineTicket(n,a,d);
                        ob.bookTicket();
                    }
                    catch(InvalidPassengerException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    catch(TicketAlreayExistsException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }while(x!=5);
    }
}
class InvalidPassengerException extends RuntimeException
{
    InvalidPassengerException(String s)
    {
        super(s);
    }
}
class TicketAlreayExistsException extends RuntimeException
{
    TicketAlreayExistsException(String s)
    {
        super(s);
    }
}
class TicketNotFoundException extends RuntimeException
{
    TicketNotFoundException(String s)
    {
        super(s);
    }
}
