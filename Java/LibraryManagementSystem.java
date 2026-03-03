import java.util.*;
import java.io.*;
class BookM
{
    int id;
    String title, author;
    int availability;

    public BookM(int id, String title, String author, int availability)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availability = availability;
    }

    public String toString()
    {
        return "Id:" + id + "\n" +
                "Tile:" + title + "\n" +
                "Author:" + author + "\n" +
                "Availability:" + availability + "\n";
    }
}
class Catalog
{
    static ArrayDeque <BookM> BookList= new ArrayDeque<>();
    Scanner sc=new Scanner(System.in);
    void addBook(int id,String n,String a,int av)
    {

        /*System.out.print("Enter name of book:");
        String n=sc.next();
        System.out.print("Enter Id of book:");
        int id=sc.nextInt();
        System.out.print("Enter author of book:");
        String a=sc.next();
        System.out.print("Enter availability of book:");
        int av=sc.nextInt();*/
        BookM ob=new BookM(id,n,a,av);
        BookList.add(ob);
    }
    void display()
    {
        System.out.println("List of all books:");
        System.out.println(BookList);
    }
    void find(int Id)
    {
        int j=-1;
        for(int i=0;i<BookList.size();i++)
        {
            BookM ob=BookList.peek();
            if(ob.id==Id)
            {
                System.out.println(ob);
                j++;
            }
        }
        if(j==-1)
        {
            System.out.println("No such book found!");
        }
    }
}
class User
{
    int userID;
    String name, contactNumber, membershipDate;
    User()
    {

    }
    public User(int userID, String name, String contactNumber, String membershipDate)
    {
        this.userID = userID;
        this.name = name;
        this.contactNumber = contactNumber;
        this.membershipDate = membershipDate;
    }

    public String toString()
    {
        return "User ID:" + userID + "\n"+
                "Name:" + name + "\n" +
                "Contact Number:" + contactNumber + "\n" +
                "Membership Date:" + membershipDate + "\n" ;
    }
}
class Transaction
{
    int bookId, userId;
    String borrowDate,returnDate;
    Scanner sc=new Scanner(System.in);

    public Transaction(int bookId, int userId)
    {
        this.bookId = bookId;
        this.userId = userId;
    }
    void borrowBook()
    {
        System.out.print("Enter the name of the book you wanna borrow:");
        String n=sc.next();
        int index=-1;
        int index1=-1;
            if(LibraryManagementSystem.user.containsKey(userId))
            {
                index++;
                for(int j=0;j<Catalog.BookList.size();j++)
                {
                    BookM ob=Catalog.BookList.peek();
                    if(ob.title.equalsIgnoreCase(n))
                    {
                        index1++;
                        if(ob.availability>0)
                        {
                            System.out.println("The book "+n+" is borrowed to you!");
                            ob.availability--;
                        }
                        else
                        {
                            System.out.println("The book "+n+" isn't currently available");
                        }
                    }
                }
        }
        if(index==-1)
        {
            System.out.println("No such User found");
        }
        else if(index1==-1)
        {
            System.out.println("No such Book found");
        }
    }
    void returnBook()
    {
        System.out.print("Enter the name of the book you are returning:");
        String n=sc.next();
        int index=-1;
        int index1=-1;
            if(LibraryManagementSystem.user.containsKey(userId))
            {
                index++;
                for(int j=0;j<Catalog.BookList.size();j++)
                {
                    BookM ob=Catalog.BookList.peek();
                    if(ob.id==bookId)
                    {
                            index1++;
                            System.out.println("The book "+n+" is returned to the Library!");
                            ob.availability++;
                    }
                }
            }
        if(index==-1)
        {
            System.out.println("No such User found");
        }
        else if(index1==-1)
        {
            System.out.println("The book \"+n+\"+ doesn't belong to the Library");
        }
    }
}
public class LibraryManagementSystem
{
    static Hashtable<Integer,User> user=new Hashtable<>();
    static ArrayDeque<Transaction> transactions=new ArrayDeque<>();
    public static void main(String[] args) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        Catalog ob=new Catalog();
        int x;
        do {
            System.out.println("Enter 1 to add book");
            System.out.println("Enter 2 to display catalog");
            System.out.println("Enter 3 to register a user");
            System.out.println("Enter 4 to display registered user");
            System.out.println("Enter 5 to borrow book");
            System.out.println("Enter 6 to return book");
            System.out.println("Enter 7 to exit");
            System.out.print("Enter your choice:");
            x=sc.nextInt();

            switch(x)
            {
                case 1:
                    System.out.print("Enter name of the book:");
                    String bn=sc.next();
                    System.out.print("Enter name of author of the book:");
                    String an=sc.next();
                    System.out.print("Enter ID of the book:");
                    int id=sc.nextInt();
                    System.out.print("Enter availability of the book:");
                    int av=sc.nextInt();
                    BookM book=new BookM(id,bn,an,av);
                    ob.addBook(id,bn,an,av);
                    BufferedWriter book_details=new BufferedWriter(new FileWriter("Books.txt"));
                    book_details.write(String.valueOf(book));
                    book_details.flush();
                    break;
                case 2:
                    System.out.println("Book catalog:");
                    ob.display();
                    break;
                case 3:
                    System.out.print("Enter userId:");
                    int uid=sc.nextInt();
                    System.out.print("Enter Name:");
                    String un=sc.next();
                    System.out.print("Enter contact number:");
                    String cn=sc.next();
                    System.out.print("Enter membership date:");
                    String d=sc.next();
                    User user1=new User(uid,un,cn,d);
                    user.put(uid,user1);
                    BufferedWriter user_details=new BufferedWriter(new FileWriter("Users.txt"));
                    user_details.write(String.valueOf(user1));
                    user_details.flush();
                    break;
                case 4:
                    System.out.println("list of registered users:");
                    System.out.println();
                    for(Map.Entry u:user.entrySet())
                    {
                        System.out.println(u.getValue());
                    }
                    break;
                case 5:
                    System.out.print("Enter user ID:");
                    int usid=sc.nextInt();
                    System.out.print("Enter book ID:");
                    int bid=sc.nextInt();
                    System.out.print("Enter borrow date int DD/MM/YYYY format:");
                    String bd=sc.next();
                    Transaction tt=new Transaction(bid,usid);
                    tt.borrowDate=bd;
                    tt.borrowBook();
                    transactions.add(tt);
                    break;
                case 6:
                    System.out.print("Enter user ID:");
                    int usid1=sc.nextInt();
                    System.out.print("Enter book ID:");
                    int bid1=sc.nextInt();
                    System.out.print("Enter return date int DD/MM/YYYY format:");
                    String rd=sc.next();
                    Transaction tt1=new Transaction(bid1,usid1);
                    tt1.returnDate=rd;
                    tt1.returnBook();
                    transactions.add(tt1);
                    break;
                case 7:
                    System.out.println("Exiting");
                    break;
            }
        }while(x!=7);
    }
}
