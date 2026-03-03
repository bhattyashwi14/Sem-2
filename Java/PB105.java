import java.util.Scanner;
//106 combined here
public class PB105
{
    static void compute(int a)
    {
       if(a%10==0)
       {
           throw new MyException("Number divisible by 10");
       }
       if(a%7==0 && a%5!=0)
       {
           throw new MyException("Number divisible by 7 but not 5");
       }
    }
    public static void main(String[] args)
    {
       Scanner sc=new Scanner(System.in);
        System.out.print("Enter your number=");
        int a=sc.nextInt();
        try
        {
            compute(a);
        }
        catch (MyException e)
        {
            System.out.println(e);
        }
    }
}
class MyException extends RuntimeException
{
    MyException(String s)
    {
        super(s);
    }
}