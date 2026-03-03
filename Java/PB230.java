import java.util.*;
public class PB230
{
    public static void main(String[] args)
    {
        ArrayList<Integer> a=new ArrayList<>((Arrays.asList(1,2,3,4,4,5,6,7,8,9,6,9,1,1,10)));
        PB230 ob=new PB230();
        System.out.println(ob.removeDuplicate(a));
    }
    ArrayList removeDuplicate(ArrayList<Integer>a)
    {
        /*for(int i=0;i<a.size();i++)
        {
            if(Collections.frequency(a,a.get(i))>1)
            {
                a.remove(i);
            }
        }*/
        for(int i=0;i<a.size();i++)
        {
            int x=a.get(i);
            boolean b=false;
            for(int j=0;j<a.size();j++)
            {
                if(i==j)
                {
                    continue;
                }
                else
                {
                    if (x==a.get(j))
                    {
                        a.remove(j);
                        b=true;
                    }
                }
            }
            if(b)
            {
                a.remove(i);
            }
        }
        return a;
    }
}
