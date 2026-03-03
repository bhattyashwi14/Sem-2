import java.util.*;
public class PB279
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter how many integers you wanna enter:");
        int n=sc.nextInt();
        PriorityQueue <Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=1;i<=n;i++)
        {
            System.out.print("Enter your number:");
            int x=sc.nextInt();
            pq.add(x);
        }
        System.out.println(pq);
        System.out.println("Highest values:");
        for(int i=0;i<3;i++)
        {
            System.out.println(pq.poll());
        }
        System.out.println("Revised List:");
        System.out.println(pq);
    }
}
