import java.util.Scanner;

public class PB108
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter humidity in percentage:");
        int h=sc.nextInt();
        System.out.print("Enter temperature in celsius:");
        int t=sc.nextInt();
        System.out.print("Enter Wind speed in km/h:");
        int w=sc.nextInt();
        try
        {
            PredictRain(t,h,w);
        }
        catch (RainException e)
        {
            System.out.println(e.getMessage());
        }
        catch (LessRainException e)
        {
            System.out.println(e.getMessage());
        }
    }
    static void PredictRain(int t,int h,int w)
    {
        if(t>=25 && h>=70 && w>=15)
        {
          throw new RainException("Please keep an umbrella with you");
        }
        else
        {
            throw new LessRainException("Less Possibility of rain. You can enjoy your day");
        }
    }
}
class RainException extends RuntimeException
{
    RainException(String s)
    {
        super(s);
    }
}
class LessRainException extends RuntimeException
{
    LessRainException(String s)
    {
        super(s);
    }
}

