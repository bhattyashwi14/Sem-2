import java.util.Scanner;
public class PB107
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter marks of subject 1:");
        int a1=sc.nextInt();
        System.out.print("Enter marks of subject 2:");
        int a2=sc.nextInt();
        System.out.print("Enter marks of subject 3:");
        int a3=sc.nextInt();
        System.out.print("Enter marks of subject 4:");
        int a4=sc.nextInt();
        System.out.print("Enter marks of subject 5:");
        int a5=sc.nextInt();
        if(a1<35||a2<35||a3<35||a4<35||a5<35)
        {
            throw new LessThan35Exception("Entered marks less than 35");
        }
    }
}
class LessThan35Exception extends RuntimeException
{
    LessThan35Exception(String s)
    {
        super(s);
    }
}
