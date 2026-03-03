class NumAlph extends Thread
{
    public void run()
    {
        if(Thread.currentThread().getName().equals("Alphabet"))
        {
            char c='a';
            for(int i=1;i<=26;i++)
            {
                System.out.println(c);
                c++;
            }
        }
        if(Thread.currentThread().getName().equals("Number"))
        {
            for(int i=1;i<=100;i++)
            {
                System.out.println(i);
            }
        }
    }
}
public class PB172
{
    public static void main(String[] args) throws Exception
    {
        NumAlph ob1=new NumAlph();
        ob1.setName("Alphabet");
        ob1.start();
        ob1.join();
        NumAlph ob2=new NumAlph();
        ob2.setName("Number");
        ob2.start();
    }
}
