class Book
{
    private String author_name;
    Book(String a)
    {
        author_name=a;
        System.out.println("Author name:"+author_name);
    }
}
class Book_Publication extends Book
{
    private String title;
    Book_Publication(String a,String t)
    {
        super(a);
        title=t;
        System.out.println("Book Title:"+title);
    }
}
class Paper_Publication extends Book
{
    private String title;
    Paper_Publication(String a,String t)
    {
        super(a);
        title=t;
        System.out.println("Paper Title:"+title);
    }
}
public class PB10
{
    public static void main(String[] args)
    {
        String a=args[0];
        String t=args[1];
        String t1=args[2];
        Book ob1=new Book_Publication(a,t);
        Book ob2=new Paper_Publication(a,t1);
    }
}
