import java.util.Scanner;
public class PB111
{
    int X;
    int Y;
    int Z;
    PB111(int [] x,int [] y,int [] z)
    {
        addCoordinates(x,y,z);
    }
    void display()
    {
        System.out.println("Resultant values are:");
        System.out.println("x="+X);
        System.out.println("y="+Y);
        System.out.println("z="+Z);
    }
    void addCoordinates(int [] x,int [] y,int [] z)
    {
        for(int i=0;i<3;i++)
        {
            X+=x[i];
            Y+=y[i];
            Z+=z[i];
        }
        if(X==0 && Y==0 && Z==0)
        {
            throw new ZeroException("The result is zero");
        }
        display();
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int [] x=new int [3];
        int [] y=new int[3];
        int [] z=new int[3];
        for(int i=0;i<3;i++)
        {
            System.out.print("Enter value of x"+(i+1)+":");
            x[i]=sc.nextInt();
            System.out.print("Enter value of y"+(i+1)+":");
            y[i]=sc.nextInt();
            System.out.print("Enter value of z"+(i+1)+":");
            z[i]=sc.nextInt();
        }
        try
        {
            PB111 ob = new PB111(x, y, z);
        }
        catch(ZeroException e)
        {
            System.out.println(e);
        }
    }
}
class ZeroException extends RuntimeException
{
    ZeroException(String s)
    {
        super(s);
    }
}
