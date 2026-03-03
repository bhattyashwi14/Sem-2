import javax.naming.LimitExceededException;
import java.util.*;
class Voters extends Thread
{
    synchronized void voting()
    {
        int i=(int)(Math.random()*Candidates.candidates.size());
        /*while(i>4 || i<0)
        {
            i=(int)Math.random()*10;
        }
        System.out.println(i);
       try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}*/

        synchronized (Candidates.candidates.get(i)) {
        Candidates.candidates.get(i).result++;}
    }
    public void run()
    {
        synchronized (this)
        {
            voting();
        }
    }
}
class Candidates
{
    String canName;
    String party;
    int age;
    int result;
    static LinkedList<Candidates> candidates=new LinkedList<>();
    Candidates(){}
    public Candidates(String canName,int age,String party)
    {
        this.canName = canName;
        this.age = age;
        this.party = party;
    }
    void result()
    {
        candidates.sort(Comparator.comparing(Candidates::getResult).reversed());
        for(int i=0;i<candidates.size();i++)
        {
            System.out.println("Candidate Name:" + candidates.get(i).canName);
            System.out.println("Party Name:" + candidates.get(i).party);
            System.out.println("Age:" + candidates.get(i).age);
            System.out.println("No. of votes:" + candidates.get(i).result);
        }
    }
    public int getResult()
    {
        return result;
    }
}
public class OnlineVotingSystem
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        Candidates can=new Candidates();
        ArrayList <Voters> voters=new ArrayList<>(50);
        for(int i=0;i<50;i++)
        {
            Voters ob=new Voters();
            voters.add(ob);
            voters.get(i).setName("User-"+(i+1));
        }
        Candidates.candidates.add(new Candidates("CVM" ,28,"AJP" ));
        Candidates.candidates.add(new Candidates("DJU",39,"BJP"));
        Candidates.candidates.add(new Candidates("HNM",35,"CJP"));
        Candidates.candidates.add(new Candidates( "HDS",32,"DJP"));
        Candidates.candidates.add(new Candidates("PAT",30,"EJP"));
        int x;
        do
        {
            System.out.println("Enter 1 to add candidate");
            System.out.println("Enter 2 to remove candidate");
            System.out.println("Enter 3 to update candidate details");
            System.out.println("Enter 4 to display candidate details");
            System.out.println("Enter 5 to cast vote");
            System.out.println("Enter 6 to view result");
            System.out.println("Enter 7 to exit");
            System.out.print("Enter your choice:");
            x=sc.nextInt();
            switch (x)
            {
                case 1:
                    System.out.print("Enter the name of candidate:");
                    String name=sc.next();
                    System.out.print("Enter the age of candidate:");
                    int age=sc.nextInt();
                    System.out.print("Enter the name party of candidate:");
                    String party=sc.next();
                    Candidates.candidates.add(new Candidates(name,age,party));
                    break;
                case 2:
                    System.out.print("Enter the name of candidate you want to remove:");
                    String n=sc.next();
                    int index=-1;
                    for(int i=0;i<Candidates.candidates.size();i++)
                    {
                        if(Candidates.candidates.get(i).canName.equalsIgnoreCase(n))
                        {
                            index=i;
                            Candidates.candidates.remove(i);
                        }
                    }
                    if(index==-1)
                    {
                        System.out.println("No such candidate found");
                    }
                    break;
                case 3:
                    System.out.print("Enter the name of candidate whose details needs to be updated:");
                    String n1=sc.next();
                    int index1=-1;
                    for(int i=0;i<Candidates.candidates.size();i++)
                    {
                        if(Candidates.candidates.get(i).canName.equalsIgnoreCase(n1))
                        {
                            index1=i;
                        }
                    }
                    if(index1==-1)
                    {
                        System.out.println("No such candidate found");
                    }
                    else
                    {
                        System.out.print("Enter updated age:");
                        int a=sc.nextInt();
                        System.out.print("Enter updated party name:");
                        String p=sc.next();
                        Candidates.candidates.get(index1).age=a;
                        Candidates.candidates.get(index1).party=p;
                        System.out.println("Details updated!");
                    }
                    break;
                case 4:
                    System.out.print("Enter the name of candidate whose details you wish to view:");
                    String na=sc.next();
                    int index2=-1;
                    for(int i=0;i<Candidates.candidates.size();i++)
                    {
                        if(Candidates.candidates.get(i).canName.equalsIgnoreCase(na))
                        {
                            index2=i;
                        }
                    }
                    if(index2==-1)
                    {
                        System.out.println("No such candidate found");
                    }
                    else
                    {
                        System.out.println("Name:"+Candidates.candidates.get(index2).canName);
                        System.out.println("Age:"+Candidates.candidates.get(index2).age);
                        System.out.println("Party:"+Candidates.candidates.get(index2).party);
                    }
                    break;
                case 5:
                    for(int i=0;i<50;i++)
                    {
                        voters.get(i).start();
                        voters.get(i).join();
                    }
                    break;
                case 6:
                     can.result();
                    break;
                case 7:
                    System.out.println("Exiting!");
                    break;
                default:
                    System.out.println("Enter a valid choice!");
            }
        }while(x!=7);
    }
}
