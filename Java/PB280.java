import java.util.*;
class Sports
{
    String teamName;
    int score;

    public Sports(String teamName, int score)
    {
        this.teamName = teamName;
        this.score = score;
    }

    public int getScore()
    {
        return score;
    }

    public String toString()
    {
        return "TeamName:" + teamName + "\n"+
                "Score:" + score +"\n";
    }
}
public class PB280
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of teams:");
        int n=sc.nextInt();
        PriorityQueue <Sports> pq=new PriorityQueue<>(Comparator.comparing(Sports::getScore).reversed());
        for(int i=0;i<n;i++)
        {
            System.out.print("Enter Team name:");
            String t=sc.next();
            System.out.print("Enter score:");
            int s=sc.nextInt();
            Sports ob=new Sports(t,s);
            pq.add(ob);
        }
        System.out.println("Teams selected for next match are:");
        for(int i=0;i<2;i++)
        {
            System.out.println(pq.poll());
        }
    }
}
