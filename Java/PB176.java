import java.util.Scanner;
class PVR extends Thread
{
    Scanner sc=new Scanner(System.in);
    static int Total_seats;
    String screeName;
    PVR(){}
    PVR(int Total_seats)
    {
        this.Total_seats=Total_seats;
    }
    public void run()
    {
        seatBooking();
    }
    synchronized void seatBooking()
    {
        System.out.print("Enter number of tickets you want to book:");
        int tickets=sc.nextInt();
        System.out.print("Enter screen name:");
        screeName=sc.next();
        /*try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {}*/
        if(tickets<=Total_seats)
        {
            System.out.println(Thread.currentThread().getName()+" is booking "+tickets+" tickets for screen "+screeName);
            Total_seats=Total_seats-tickets;
            System.out.println("Tickets booked successfully, "+Total_seats+" seats available now");
        }
        else
        {
            System.out.println("Sorry! "+Thread.currentThread().getName()+" cannot book "+tickets+" tickets for screen "
                    +screeName+" because not enough seats available");
            System.out.println("Available seats:"+Total_seats);
        }
    }
}
public class PB176
{
    public static void main(String[] args) throws Exception
    {
        PVR ob=new PVR(5);
        PVR ob1=new PVR();
        ob1.start();
        ob1.join();
        PVR ob2=new PVR();
        ob2.start();
    }
}
