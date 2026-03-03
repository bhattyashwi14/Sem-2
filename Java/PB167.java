class OddEven extends Thread
{
    public void run()
    {
        if(Thread.currentThread().getName().equals("Odd"))
        {
            for(int i=1;i<=100;i++)
            {
                if(i%2!=0)
                {
                    System.out.println(i);
                }
            }
        }
        if(Thread.currentThread().getName().equals("Even"))
        {
            for(int i=1;i<=100;i++)
            {
                if(i%2==0)
                {
                    System.out.println(i);
                }
            }
        }
    }
}
public class PB167
{
    public static void main(String[] args) throws Exception
    {
        OddEven ob1=new OddEven();
        ob1.setName("Odd");
        ob1.start();
        ob1.join();
        OddEven ob2=new OddEven();
        ob2.setName("Even");
        ob2.start();
    }
}