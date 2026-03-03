import java.util.Scanner;
class AddOddEven extends Thread
{
    int n;
    AddOddEven(int n)
    {
        this.n=n;
    }
   public void run()
   {
       if(Thread.currentThread().getName().equals("Odd"))
       {
           int sum=0;
           for(int i=1;i<=n;i++)
           {
               if(i%2!=0)
               {
                  sum=sum+i;
               }
           }
           System.out.println("Sum of odd numbers="+sum);
       }
       if(Thread.currentThread().getName().equals("Even"))
       {
           int sum=0;
           for(int i=1;i<=n;i++)
           {
               if(i%2==0)
               {
                   sum=sum+i;
               }
           }
           System.out.println("Sum of even numbers="+sum);
       }
   }
}
public class PB171
{
    public static void main(String[] args) throws Exception
    {
       Scanner sc=new Scanner(System.in);
        System.out.print("Enter the value of n:");
        int n=sc.nextInt();
        AddOddEven ob1=new AddOddEven(n);
        ob1.setName("Odd");
        ob1.start();
        ob1.join();
        AddOddEven ob2=new AddOddEven(n);
        ob2.setName("Even");
        ob2.start();
    }
}
