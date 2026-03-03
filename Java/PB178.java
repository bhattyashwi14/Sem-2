import java.util.Scanner;

class Prime implements Runnable
{
    int x;
    int j;
    Prime(){}
    Prime(int x,int j)
    {
        this.x=x;
        this.j=j;
    }
    public void run()
    {
        synchronized (this)
        {
            checkPrime(j);
        }
    }
    synchronized void checkPrime(int j)
    {
        int count=0;
        for(int i=1;i<=x;i++)
        {
            if(x%i==0)
            {
                count++;
            }
        }
        //System.out.println(count);
        if(count<=2)
        {
            System.out.println("The number "+x+" at index "+j+" is prime");
        }
        else
        {
            System.out.println("The number "+x+" at index "+j+" is not prime");
        }
    }
}
public class PB178
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("The the number of elements:");
        int n=sc.nextInt();
        PB178 ob=new PB178();
        int [] num=new int[n];
        for(int i=0;i<n;i++)
        {
            System.out.print("Enter elements at num["+i+"]:");
            num[i]=sc.nextInt();
        }
        Thread [] t=new Thread[n];
        for(int i=0;i<n;i++)
        {
            Prime p=new Prime();
            Prime p1=new Prime(num[i],i);
            t[i]=new Thread(p1);
            t[i].start();
            t[i].join();
        }
        for(int i=0;i<n;i++)
        {
            if(t[i].isAlive())
            {
                System.out.println(t[i].getName()+" is still running");
            }
        }
    }
}
