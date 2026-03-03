public class PB103
{
    static int pow(int x,int y)
    {
        if(x<0 || y<0)
        {
            throw new InvalidNumberException ("Numbers should be greater than zero");
        }
        if(x==0)
        {
            throw new IndeterminateFormException("Base number can't be zero");
        }
        int result=1;
        for(int i=0;i<y;i++)
        {
            result=result*x;
        }
        return result;
    }

    public static void main(String[] args)
    {
        try
        {
            int x=Integer.parseInt(args[0]);
            int y=Integer.parseInt(args[1]);
            System.out.println(x + "^" + y + "=" + pow(x, y));
        }
        catch (InvalidNumberException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IndeterminateFormException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
class InvalidNumberException extends RuntimeException
{
    InvalidNumberException(String s)
    {
        super(s);
    }
}
class IndeterminateFormException extends RuntimeException
{
    IndeterminateFormException(String s)
    {
        super(s);
    }
}