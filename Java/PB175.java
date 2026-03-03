import java.util.Scanner;
class Matrix extends Thread
{
    int i;
    int [] x;
    int sum;
    Matrix(int i, int[] x)
    {
        this.i=i;
        this.x=x;
    }
    public void run()
    {
        sum();
    }
    void sum()
    {
        for(int i=0;i<x.length;i++)
        {
            sum=sum+x[i];
        }
        System.out.println("Sum of elements of row "+i+"="+sum);
    }
}
public class PB175
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of rows:");
        int r=sc.nextInt();
        System.out.print("Enter number of columns:");
        int c=sc.nextInt();
        Matrix [] m=new Matrix[r];
        int [][] a=new int[r][c];
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                System.out.print("Enter element at a["+i+"]["+j+"]:");
                a[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                System.out.println("a["+i+"]["+j+"]:"+a[i][j]);
            }
        }
        for(int i=0;i<r;i++)
        {
             m[i]=new Matrix(i,a[i]);
             m[i].start();
        }
    }
}
