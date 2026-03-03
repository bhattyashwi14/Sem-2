import java.util.*;
class Participants extends Thread
{
    String Name;
    int age;
    String contactNumber;
    Scanner sc=new Scanner(System.in);
    ArrayList <Character> pans=new ArrayList<>();
    int score;
    public Participants(String Name, int age, String contactNumber)
    {
        this.Name = Name;
        this.age = age;
        boolean b=false;
        while(b==false)
        {
            if(contactNumber.length()==10 && contactNumber.charAt(0)=='9')
            {
                this.contactNumber = contactNumber;
                b=true;
            }
            else
            {
                if(contactNumber.length()!=10)
                {
                    System.out.print("Please enter a 10 digit mobile number:");
                    contactNumber=sc.next();
                }
                else if(contactNumber.charAt(0)!=9)
                {
                    System.out.print("Please Enter a number beginning with 9:");
                    contactNumber=sc.next();
                }
            }
        }
    }

    public int getAge()
    {
        return age;
    }
    synchronized public void run()
    {
        System.out.println(getName1()+" is answering the quiz:");
        System.out.println();
        System.out.println("Q.1. National bird of INDIA..(A) Peacock (B) Sparrow (C) Duck (D) Owl"+
        "\n Q.2 Independence year of INDIA..(A) 1955 (B) 1947 (C) 1999 (D) 1929 "+
        "\n Q.3 Gandhi Jayanti is on......(A) 2nd Oct (B) 5th Oct (C) 9th Oct (D) 7th Oct "+
        "\n Q.4 Count of states in INDIA..(A) 17 (B) 21 (C) 25 (D) 28"+
        "\n Q.5 how many continents are there in the world..(A) 5 (B) 6 (C) 7 (D) 8");
        System.out.println();
        System.out.println("Please answer using capital alphabets!");
        for(int i=1;i<=5;i++)
        {
            System.out.print("Enter your answer for Question "+i+":");
            char x=sc.next().charAt(0);
            pans.add(x);
        }
        result();
    }
    void result()
    {
        PB246 ob=new PB246();
        for(int i=0;i<5;i++)
        {
           if(ob.answers.get(i)==pans.get(i))
           {
               score++;
           }
        }
    }

    public int getScore()
    {
        return score;
    }

    public String getName1()
    {
        return Name;
    }
}
public class PB246
{
    static Stack <Character> answers=new Stack<>();
    public static void main(String[] args) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        ArrayList <Participants> Participant=new ArrayList<>();
       for(int i=1;i<=3;i++)
       {
           System.out.print("Enter name of participant " + i + ":");
           String n = sc.next();
           System.out.print("Enter age of participant " + i + ":");
           int a = sc.nextInt();
           System.out.print("Enter contact number of participant " + i + ":");
           String cn = sc.next();
           Participants ob = new Participants(n, a, cn);
           Participant.add(ob);
       }

           answers.push('A'); answers.push('B'); answers.push('A'); answers.push('D');
           answers.push('C');
           Participant.sort(Comparator.comparing(Participants:: getAge));
           for(int i=0;i<3;i++)
           {
               Thread t=new Thread(Participant.get(i));
               Participant.get(i).start();
               Participant.get(i).join();
           }
        Participant.sort(Comparator.comparing(Participants::getScore).reversed());
           
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                if(i==j)
                {
                    continue;
                }
                else
                {
                    if (Participant.get(i).score==Participant.get(j).score)
                    {
                        if(Participant.get(i).age<Participant.get(j).age)
                        {System.out.println("Winner is: "+Participant.get(i).getName1()); break;}
                        else
                        {System.out.println("Winner is: "+Participant.get(j).getName1()); break;}
                    }
                    else
                    {
                        System.out.println("Winner is: "+Participant.get(0).getName1());
                        break;
                    }
                }
            }
        }
    }
}
