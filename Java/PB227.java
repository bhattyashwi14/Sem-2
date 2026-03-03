import java.util.*;
public class PB227
{
    public static void main(String[] args)
    {
        ArrayList<Integer> even=new ArrayList<>();
        ArrayList<Integer> odd=new ArrayList<>();
        ArrayList<Integer> merge=new ArrayList<>();
        for(int i=1;i<=10;i++)
        {
            if(i%2==0)
            {
                even.add(i);
            }
            else
            {
                odd.add(i);
            }
        }
        merge.addAll(odd);
        merge.addAll(even);
        //Collections.sort(merge,Comparator.reverseOrder());
        Collections.sort(merge.reversed());
        System.out.println(merge);
    }
}
