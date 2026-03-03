import java.util.*;
import java.io.*;
class Captain
{
    String name;
    int age;
    String team_affiliation;
    int votes;
    double votePercentage;

    public Captain(String name, int age, String team_affiliation)
    {
        this.name = name;
        this.age = age;
        this.team_affiliation = team_affiliation;
    }

    public String toString()
    {
        return "Name:" + name + "\n"+
                "Age:" + age +"\n"+
                "Team_affiliation:" + team_affiliation + "\n";
    }

    public int getCaptainVotes()
    {
        return votes;
    }
    void calculateVotePercentage()
    {
        votePercentage=(votes*100)/50;
    }
}
public class IPLVoting
{
    static HashMap <String,Captain> hm=new HashMap<>();
    public static void main(String[] args) throws Exception
    {
        hm.put("Rohit",new Captain("Rohit",32,"MI"));
        hm.put("Dhoni",new Captain("Dhoni",39,"CSK"));
        hm.put("Virat",new Captain("Virat",30,"RCB"));
        hm.put("Hardik",new Captain("Hardik",31, "GT"));
        hm.put("Rishabh",new Captain("Rishabh",29,"DC"));
        System.out.println("List of Candidates:");
        System.out.println(hm.get("Rohit"));
        System.out.println(hm.get("Dhoni"));
        System.out.println(hm.get("Virat"));
        System.out.println(hm.get("Hardik"));
        System.out.println(hm.get("Rishabh"));

        ArrayDeque <String> ad=new ArrayDeque<>();
        BufferedWriter bw=new BufferedWriter(new FileWriter("Voting Reult.txt"));
        bw.write(String.valueOf(hm.get("Rohit")));
        bw.write(String.valueOf(hm.get("Dhoni")));
        bw.write(String.valueOf(hm.get("Virat")));
        bw.write(String.valueOf(hm.get("Hardik")));
        bw.write(String.valueOf(hm.get("Rishabh")));
        bw.flush();
                for(int i=1;i<=50;i++)
                {
                    Voter voter=new Voter("Voter-"+i);
                    String s=voter.name+" voted for "+voter.voting();
                    ad.add(s);
                    bw.write(s);
                    bw.newLine();
                    bw.flush();
                }
                HashMap<String,Integer> votes=new HashMap<>();
                votes.put("Rohit",hm.get("Rohit").votes);
                votes.put("Dhoni",hm.get("Dhoni").votes);
                votes.put("Virat",hm.get("Virat").votes);
                votes.put("Hardik",hm.get("Hardik").votes);
                votes.put("Rishabh",hm.get("Rishabh").votes);
                ArrayList <Captain> voteCopy=new ArrayList<>();
                voteCopy.add(hm.get("Rohit"));
                voteCopy.add(hm.get("Dhoni"));
                voteCopy.add(hm.get("Virat"));
                voteCopy.add(hm.get("Hardik"));
                voteCopy.add(hm.get("Rishabh"));
                voteCopy.sort(Comparator.comparing(Captain::getCaptainVotes).reversed());
        System.out.println("The Results are:");
        bw.write("The Results are:"); bw.newLine(); bw.flush();
        System.out.println(hm.get("Rohit")+"Votes:"+hm.get("Rohit").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Rohit").votePercentage+"\n");
        System.out.println(hm.get("Dhoni")+"Votes:"+hm.get("Dhoni").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Dhoni").votePercentage+"\n");
        System.out.println(hm.get("Virat")+"Votes:"+hm.get("Virat").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Virat").votePercentage+"\n");
        System.out.println(hm.get("Hardik")+"Votes:"+hm.get("Hardik").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Hardik").votePercentage+"\n");
        System.out.println(hm.get("Rishabh")+"Votes:"+hm.get("Rishabh").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Rishabh").votePercentage+"\n");

        System.out.println("The winner is:");
        hm.get(voteCopy.get(0).name);
        System.out.println(hm.get(voteCopy.get(0).name)+"Votes:"+hm.get(voteCopy.get(0).name).votes+"\n"
        +"Vote Percentage:"+hm.get(voteCopy.get(0).name).votePercentage+"\n");

        bw.write(hm.get("Rohit")+"Votes:"+hm.get("Rohit").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Rohit").votePercentage+"\n");
        bw.write(hm.get("Dhoni")+"Votes:"+hm.get("Dhoni").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Dhoni").votePercentage+"\n");
        bw.write(hm.get("Virat")+"Votes:"+hm.get("Virat").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Virat").votePercentage+"\n");
        bw.write(hm.get("Hardik")+"Votes:"+hm.get("Hardik").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Hardik").votePercentage+"\n");
        bw.write(hm.get("Rishabh")+"Votes:"+hm.get("Rishabh").getCaptainVotes()+"\n"+"Vote Percentage:"
                +hm.get("Rishabh").votePercentage+"\n");
        bw.flush();
        bw.write("The winner is:");
        bw.newLine();
        bw.write(String.valueOf(hm.get(voteCopy.get(0).name)));
        bw.flush();
    }
}
class Voter
{
    String name;

    public Voter(String name)
    {
        this.name = name;
    }

    String voting()
    {
        String s="";
        int x=(int)(Math.random()*10);
        while(x>5 || x<1)
        {
            x=(int)(Math.random()*10);
        }
        if(x==1)
        {
            IPLVoting.hm.get("Rohit").votes++;
            IPLVoting.hm.get("Rohit").calculateVotePercentage();
            s="Rohit";
        }
        else if(x==2)
        {
            IPLVoting.hm.get("Dhoni").votes++;
            IPLVoting.hm.get("Dhoni").calculateVotePercentage();
            s="Dhoni";
        }
        else if(x==3)
        {
            IPLVoting.hm.get("Virat").votes++;
            IPLVoting.hm.get("Virat").calculateVotePercentage();
            s="Virat";
        }
        else if(x==4)
        {
            IPLVoting.hm.get("Hardik").votes++;
            IPLVoting.hm.get("Hardik").calculateVotePercentage();
            s="Hardik";
        }
        else if(x==5)
        {
            IPLVoting.hm.get("Rishabh").votes++;
            IPLVoting.hm.get("Rishabh").calculateVotePercentage();
            s="Rishabh";
        }
        return s;
    }
}
