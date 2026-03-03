class Bowler extends Thread
{
    public void run()
    {
       bowling();
    }
    synchronized void bowling()
    {
        if(Thread.currentThread().getName().equals("Yorker"))
        {
            System.out.println("SIXER");
        }
        if(Thread.currentThread().getName().equals("Googly"))
        {
            System.out.println("WICKET");
        }
    }
}
public class PB165
{
    public static void main(String[] args)
    {
        Bowler ob1=new Bowler();
        ob1.setName("Yorker");
        ob1.start();
        Bowler ob2=new Bowler();
        ob2.setName("Googly");
        ob2.start();
    }
}
