public class PB109
{
    public static void main(String[] args)
    {
        PB109 ob=new PB109();
        int m=0;
        int [][] statusref = new int[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j=0;j<3;j++)
            {
                statusref[i][j]=Integer.parseInt(args[m]);
                m++;
            }
        }
        try
        {
           ob.processStatusCount(statusref);
        }
        catch(OFException e)
        {
            System.out.println(e);
            for (int i = 0; i < 3; i++)
            {
                for (int j=0;j<3;j++)
                {
                    if(statusref[i][j]==2)
                    {
                        statusref[i][j]=0;
                        ob.f++;
                        ob.in--;
                    }
                }
            }
            System.out.println("Updated values:");
            System.out.println("Free resources="+ob.f);
            System.out.println("Occupied resources="+ob.o);
            System.out.println("Inaccessible resources="+ob.in);
        }
    }
    int f,o,in;
    void processStatusCount(int [][] a)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j=0;j<3;j++)
            {
                if(a[i][j]==0)
                {
                    f++;
                }
                if(a[i][j]==1)
                {
                    o++;
                }
                if(a[i][j]==2)
                {
                    in++;
                }
            }
        }
        System.out.println("Total free resources:"+f);
        System.out.println("Total occupied resources:"+o);
        System.out.println("Total inaccessible resources:"+in);
        if(o>f)
        {
            throw new OFException("Total occupied resources exceeds total free resources");
        }
    }
}
class OFException extends RuntimeException
{
    OFException(String s)
    {
        super(s);
    }
}