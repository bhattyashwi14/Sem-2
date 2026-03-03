public class PB104
{
    public static void main(String[] args)
    {
        if(args.length==0)
        {
            throw new ArrayIndexOutOfBound("No string entered") ;
        }
        String s=args[0];
        if(s.charAt(0)>65 && s.charAt(0)<90)
        {
            System.out.println("Entered String:"+s);
        }
        else
        {
            throw new NoUpperCaseException("Upper case not found");
        }
    }
}
class ArrayIndexOutOfBound extends RuntimeException
{
    ArrayIndexOutOfBound(String s)
    {
        super(s);
    }
}
class NoUpperCaseException extends RuntimeException
{
    NoUpperCaseException(String s)
    {
        super(s);
    }
}
