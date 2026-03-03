class Caller extends Thread
{
    Receiver r;
    Caller(Receiver r)
    {
        this.r=r;
    }
    public void run()
    {
        Receiver.receiver();
    }
}
class Receiver
{
    synchronized static void receiver()
    {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Ringing");
    }
}
public class PB177
{
    public static void main(String[] args)
    {
        Receiver ob=new Receiver();
        Caller c1=new Caller(ob);
        Caller c2=new Caller(ob);
        c1.start();
        c2.start();
    }
}
