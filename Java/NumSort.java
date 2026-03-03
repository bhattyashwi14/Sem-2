import java.util.*;
import java.io.*;
public class NumSort
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter no. of integers you want to sort:");
        int n=sc.nextInt();
        BufferedWriter bw=new BufferedWriter(new FileWriter("Number.txt"));
        ArrayList<Integer>num=new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            System.out.println("Enter your number:");
            int x=sc.nextInt();
            bw.write(x);
            bw.newLine();
        }
        bw.close();
        BufferedReader br=new BufferedReader(new FileReader("Number.txt"));
        String l=br.readLine();
        while(br!=null)
        {
            String [] s=new String[1];
            s[0]=l;
            int y=Integer.parseInt(s[0]);
            num.add(y);
            l=br.readLine();
        }
        BufferedWriter bw1=new BufferedWriter(new FileWriter("sort.txt"));
        num.sort(Comparator.naturalOrder());
        for(int i=0;i<num.size();i++)
        {
            bw1.write(num.get(i));
            bw1.newLine();
        }
        bw1.close();
        br.close();
    }
}
