public class PB110
{
    public static void main(String[] args)
    {
       PB110 ob=new PB110();

            for (int i = 0; i < args.length; i++)
            {
                try
                {
                    int a = Integer.parseInt(args[i]);
                    ob.sum(a);
                }

                catch(NegativeNumberException e)
                {
                    System.out.println(e.getMessage());
                }
                catch(DivisibleBy10Exception e)
                {
                    System.out.println(e.getMessage());
                }
                catch(GreaterNumberException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        System.out.println("SUM="+ob.sum);
    }
    int sum=0;
    void sum(int a)
    {
        if(a<0)
        {
            throw new NegativeNumberException("The entered number is negative");
        }
        if(a%10==0)
        {
            throw new DivisibleBy10Exception("Entered number is divisible by 10") ;
        }
        if(a>1000 && a<2000)
        {
            throw new GreaterNumberException("Entered number is greater than 1000 and less than 2000");
        }
        if(a>7000)
        {
            throw new GreaterNumberException("Entered number is greater than 7000");
        }
        sum=sum+a;
    }
}
class NegativeNumberException extends RuntimeException
{
    NegativeNumberException(String s)
    {
        super(s);
    }
}
class DivisibleBy10Exception extends RuntimeException
{
    DivisibleBy10Exception(String s)
    {
        super(s);
    }
}
class GreaterNumberException extends RuntimeException
{
    GreaterNumberException(String s)
    {
        super(s);
    }
}