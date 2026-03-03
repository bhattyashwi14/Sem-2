//same as 168
//producer consumer
public class Alfredo
{
    int order;
    boolean isAlfredoReady=false;
    synchronized void produce(int count) throws Exception
    {
        while(isAlfredoReady)
        {
            wait();
        }
        order=count;
        isAlfredoReady=true;
        System.out.println("Oder no. "+order+" Alfredo pasta are ready");
        notify();
    }
    synchronized void consume() throws Exception
    {
        while(!isAlfredoReady)
        {
            wait();
        }
        System.out.println("Alfredo pasta consumed by consumer");
        isAlfredoReady=false;
        notify();
    }
}
class Producer extends Thread
{
    Alfredo bowl;
    Producer(Alfredo bowl)
    {
        this.bowl=bowl;
    }
    public void run()
    {
        for(int i=1;i<=5;i++)
        {
            try
            {
            bowl.produce(i);}
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class Consumer extends Thread
{
    Alfredo bowl;
    Consumer(Alfredo bowl)
    {
        this.bowl=bowl;
    }
    public void run()
    {
        for(int i=1;i<=5;i++)
        {
            try
            {
                bowl.consume();}
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class RunShop
{
    public static void main(String[] args)
    {
        Alfredo bowl=new Alfredo();
        Producer p=new Producer(bowl);
        Consumer c=new Consumer(bowl);
        p.start(); c.start();
    }
}

