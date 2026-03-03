import java.io.*;
import java.util.*;
import java.sql.*;
import java.sql.Timestamp;


class Reflecto
{
    static Connection con;
    {
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Reflectoy", "root", "");
            if (con!=null)
            {
                //System.out.println("Connection done");
            }
        }
        catch (Exception e)
        {
            System.out.println("Connection Error");
        }
    }
    static Scanner sc=new Scanner(System.in);
    static LoginSignup ob=new LoginSignup();
    public static void main(String[] args) throws Exception
    {


        int x;
        do {
            System.out.println();
            System.out.println("ENTER 1 FOR JOURNAL");
            System.out.println("ENTER 2 FOR TO-DO LIST");
            System.out.println("ENTER 3 FOR CHALLENGES");
            System.out.println("ENTER 4 FOR POSTS");
            System.out.println("ENTER 5 FOR EXIT");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE:");
            x=sc.nextInt(); sc.nextLine();
            switch (x)
            {
                case 1:
                {
                    Journal j = new Journal();
                    j.journalMenu();
                }
                break;
                case 2:
                    To_Do_List td=new To_Do_List();
                    td.todoListMenu();
                    break;
                case 3:
                    break;
                case 4:
                    Posts p=new Posts();
                    p.postsMenu(LoginSignup.userId);
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Exiting!!!");
                    break;
                default:
                    System.out.println("Enter valid choice");
            }
        }while(x!=5);
    }
}

class LoginSignup
{
    String userName;
    String passCode;
    static int userId;
    LoginSignup()
    {
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Reflectoy","root","");
            if(con!=null)
            {
                //System.out.println("Connection done");
            }
            Scanner sc=new Scanner(System.in);
            while(true)
            {
                System.out.println("Login/SignUp/Exit");
                String loginInput=sc.next();
                if (loginInput.equalsIgnoreCase("SignUP"))
                {
                    while (true)
                    {
                        while (true)
                        {
                            System.out.print("Create UserName:");
                            userName = sc.next().toLowerCase();
                            try
                            {
                                if (!isUserNameUnique(con, userName))
                                {
                                    System.out.println("This username is not available. Please try another.");
                                    continue;
                                }
                                LoginSignup.signUpUserName(userName);
                                break;
                            }
                            catch (LengthException e)
                            {
                                System.out.println(e);
                            }
                            catch (EmptyDataException e)
                            {
                                System.out.println(e);
                            }
                            catch (DigitException e)
                            {
                                System.out.println(e);
                            }
                        }
                        while (true)
                        {
                            System.out.print("Create PassCode:");
                            passCode = sc.next();
                            try
                            {
                                LoginSignup.signUpPassCode(passCode);
                                break;
                            } catch (LengthException e)
                            {
                                System.out.println(e);
                            }
                            catch (EmptyDataException e)
                            {
                                System.out.println(e);
                            }
                        }
                        String getUserID="{call getNewUserId()}";
                        CallableStatement cst=con.prepareCall(getUserID);
                        ResultSet rs=cst.executeQuery();
                        rs.next();
                        userId=rs.getInt(1);
                        System.out.println("Signed in Successfully!!!");
                        String insertUser="{call insertUser(?,?,?)}";
                        CallableStatement cst1=con.prepareCall(insertUser);
                        cst1.setInt(1,userId); cst1.setString(2,userName);
                        cst1.setString(3,passCode);
                        cst1.executeUpdate();
                        break;
                    }
                }
                else if (loginInput.equalsIgnoreCase("Login"))
                {
                    while(true)
                    {
                        System.out.print("Enter username:");
                        userName=sc.next().toLowerCase();
                        String loginUser="{call loginUser(?)}";
                        CallableStatement cst2=con.prepareCall(loginUser);
                        cst2.setString(1,userName);
                        ResultSet rs2=cst2.executeQuery();
                        if(rs2.next())
                        {
                            while(true)
                            {
                                System.out.print("Enter passcode:");
                                passCode=sc.next();
                                String checkPasscode="{call checkPasscode(?,?)}";
                                CallableStatement cst3=con.prepareCall(checkPasscode);
                                cst3.setString(1,userName);
                                cst3.executeQuery();
                                if(cst3.getString(2).equals(passCode))
                                {
                                    System.out.println("Logged in successfully!");
                                    Quotes.displayRandomQuote();
                                    String uid="{call getUserId(?,?,?)}";
                                    CallableStatement cst1=con.prepareCall(uid);
                                    cst1.setString(2,userName);
                                    cst1.setString(3,passCode);
                                    cst1.executeQuery();
                                    userId=cst1.getInt(1);
                                    break;
                                }
                                else
                                {
                                    System.out.println("The passcode is incorrect for the entered username");
                                }
                            }
                            break;
                        }
                        else
                        {
                            System.out.println("No such username found");
                        }
                    }
                    break;
                }
                else if(loginInput.equalsIgnoreCase("exit"))
                {
                    System.out.println("Exiting!!!");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Connection Error");
        }
    }
    static void signUpUserName(String userName)
    {
        if(userName.length()==0)
        {
            throw new EmptyDataException("User name can't be empty");
        }
        if(userName.length()<4 || userName.length()>20)
        {
            throw new LengthException("The length of user name should be greater than 4 and less than 20");
        }
        if(Character.isDigit(userName.charAt(0)))
        {
            throw new DigitException("The First character of user name should not be a digit");
        }
    }

    static void signUpPassCode(String passCode)
    {
        if(passCode.length()==0)
        {
            throw new EmptyDataException("Passcode can't be empty");
        }
        if(passCode.length()<8)
        {
            throw new LengthException("Passcode should be of minimum 8 character");
        }
        if(passCode.length()>15)
        {
            throw new LengthException("Passcode shouldn't be longer than 15 characters");
        }
    }

    static boolean isUserNameUnique(Connection con, String newUserName) throws Exception
    {
        SinglyLinkedList list = new SinglyLinkedList();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT username FROM users");
        while (rs.next())
        {
            list.insertAtLast(rs.getString(1).toLowerCase());
        }
        SinglyLinkedList.node temp = list.first;
        while (temp != null)
        {
            if (temp.data.equalsIgnoreCase(newUserName))
            {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

}
class LengthException extends RuntimeException
{
    LengthException(String s)
    {
        super(s);
    }
}
class EmptyDataException extends RuntimeException
{
    EmptyDataException(String s)
    {
        super(s);
    }
}
class DigitException extends RuntimeException
{
    DigitException(String s)
    {
        super(s);
    }
}

class Quotes
{
    static Connection con;
    static{
        try
        {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Reflectoy", "root", "");
            if (con!=null)
            {
                //System.out.println("Connection done");
            }
        }
        catch (Exception e)
        {
            System.out.println("Connection Error");
        }
    }
    static void displayRandomQuote() throws Exception
    {
        String q = "SELECT quote FROM quotes ORDER BY RAND() LIMIT 5";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(q);
        SinglyLinkedList quoteList = new SinglyLinkedList();
        while (rs.next())
        {
            quoteList.insertAtLast(rs.getString("quote"));
        }
        int randomIndex = (int)(Math.random() * 5);
        SinglyLinkedList.node temp = quoteList.first;
        for (int i = 0; i < randomIndex; i++)
        {
            temp = temp.next;
        }
        System.out.println();
        System.out.println("💬 Quote of the moment:");
        System.out.println(temp.data);
    }
}

class Deque
{
    int cap,front,rear;
    String [] Q;
    Deque(int size)
    {
        cap=size;
        front=-1;
        rear=-1;
        Q=new String [cap];
    }
    void insertAtRear(String y)
    {
        if((front==rear+1) || (front==0 && rear==cap-1))
        {
            System.out.println("Overflow");
        }
        else
        {
            rear=(rear+1)%cap;
            Q[rear]=y;
            if(front==-1)
            {
                front=0;
            }
        }
    }
    void insertAtFront(String y)
    {
        if((front==rear+1) || (front==0 && rear==cap-1))
        {
            System.out.println("Overflow");
        }
        else
        {
            if(front==-1)
            {
                front=0;
                rear=0;
                Q[front]=y;
            }
            else if(front==0)
            {
                front=cap-1;
                Q[front]=y;
            }
            else
            {
                front=front-1;
                Q[front]=y;
            }
        }
    }
    String deleteAtFront()
    {
        if(front<0)
        {
            System.out.println("The queue is empty");
            return null;
        }
        else
        {
            String y=Q[front];
            if(front==rear)
            {
                front=-1;
                rear=-1;
            }
            else
            {
                front=(front+1)%cap;
            }
            return y;
        }
    }
    String deleteAtRear()
    {
        if(front==-1)
        {
            System.out.println("The queue is empty");
            return null;
        }
        else
        {
            String y=Q[rear];
            if(front==rear)
            {
                front=-1;
                rear=-1;
                return null;
            }
            else if(rear==0)
            {
                rear=cap-1;
            }
            else
            {
                rear=rear-1;
            }
            return y;
        }
    }
    void display()
    {
        if(front==-1)
        {
            System.out.println("The queue is empty");
        }
        else
        {
            int i=front;
            do {
                System.out.println(Q[i]);
                i=(i+1)%cap;
            }while(i!=(rear+1)%cap);
        }
    }
}

class SinglyLinkedList
{
    class node
    {
        String data;
        node next;
        node(String data)
        {
            this.data=data;
            this.next=null;
        }
    }
    node first=null;
    void insertAtFront(String y)
    {
        node n=new node(y);
        if(first==null)
        {
            first=n;
        }
        else
        {
            n.next=first;
            first=n;
        }
    }
    void display()
    {
        if(first==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            node temp=first;
            while(temp!=null)
            {
                System.out.println(temp.data);
                temp=temp.next;
            }
        }
    }
    void insertAtLast(String y)
    {
        node n=new node(y);
        node temp=first;
        if(first==null)
        {
            first=n;
        }
        else
        {
            while(temp.next!=null)
            {
                temp=temp.next;
            }
            temp.next=n;
        }
    }
    node deleteAtFirst()
    {
        node del=first;
        if(first==null)
        {
            System.out.println("The list is empty");
            return null;
        }
        else
        {
            first = first.next;
            del.next = null;
            return del;
        }
    }
    node deleteAtLast()
    {
        if(first==null)
        {
            System.out.println("The list is empty");
            return null;
        }
        else if(first.next==null)
        {
            node del=first;
            first=null;
            return del;
        }
        else
        {
            node temp=first;
            node del=first;
            while(temp.next.next!=null)
            {
                temp=temp.next;
            }
            del=temp.next;
            temp.next=null;
            return del;
        }
    }
}

class To_Do_List
{
    static Connection con;
    static int userId;
    static
    {
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Reflectoy", "root", "");
            if (con!=null)
            {
                //System.out.println("Connection done");
                String uid="{call getUserId(?,?,?)}";
                CallableStatement cst1=con.prepareCall(uid);
                cst1.setString(2,Reflecto.ob.userName);
                cst1.setString(3,Reflecto.ob.passCode);
                cst1.executeQuery();
                userId=cst1.getInt(1);
            }
        }
        catch (Exception e)
        {
            System.out.println("Connection Error");
        }
    }
    static Scanner sc=new Scanner(System.in);
    static int count=0;
    static Deque obb=new Deque(100);

    static void todoListMenu() throws Exception
    {
        System.out.println();
        int x2;
        do {
            System.out.println();
            System.out.println("TO-DO LIST:");
            System.out.println("ENTER 1 TO CREATE TO-DO LIST");
            System.out.println("ENTER 2 TO EDIT TO-DO LIST");
            System.out.println("ENTER 3 TO DELETE TO-DO LIST");
            System.out.println("ENTER 4 TO SEARCH/VIEW TO-DO LIST");
            System.out.println("ENTER 5 TO MARK TASKS AS DONE");
            System.out.println("ENTER 6 TO EXIT");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE:");
            x2=sc.nextInt(); sc.nextLine();

            switch (x2)
            {
                case 1:
                    System.out.println();
                    To_Do_List.createToDoList();
                    break;
                case 2:
                    System.out.println();
                    To_Do_List.edit_list();
                    break;
                case 3:
                    System.out.println();
                    To_Do_List.deleteList();
                    break;
                case 4:
                    System.out.println();
                    To_Do_List.searchList();
                    break;
                case 5:
                    System.out.println();
                    To_Do_List.markAsDone();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("Exiting from to-do list!");
                    break;
                default:
                    System.out.println("Enter a valid choice!");
            }
        }while(x2!=6);
    }
    static void createToDoList() throws Exception
    {
        System.out.print("Enter title for to-do list:");
        String name=sc.nextLine();
        System.out.println("Write end when you're done adding tasks!");
        String tasks="";
        while(true)
        {
            count++;
            System.out.println("Enter task " + count + ". :");
            tasks = sc.nextLine();
            if (tasks.equalsIgnoreCase("end"))
            {break;}
            obb.insertAtRear(tasks);
        }
        String q = "INSERT INTO To_do_list (title,user_id) VALUES ('" + name + "',"+userId+")";
        Statement st=con.createStatement();
        st.executeUpdate(q);
        String q1="Update to_do_list set list=? where title='"+name+"' and user_id="+userId;
        PreparedStatement pst=con.prepareStatement(q1);
        String list="";
        for(int i=1;i<count;i++)
        {
            list+=i+". "+obb.deleteAtFront()+"\n";
        }
        pst.setString(1,list);
        int n=pst.executeUpdate();
        if(n>0)
        {
            System.out.println(n+" to-do list added successfully!");
        }
        Quotes.displayRandomQuote();
    }

    static void edit_list() throws Exception
    {
        int x;
        do {
            System.out.println("EDIT TO-DO LIST");
            System.out.println("ENTER 1 TO ADD MORE TASKS");
            System.out.println("ENTER 2 TO DELETE TASKS");
            System.out.println("ENTER 3 TO CHANGE OR RENAME TASKS");
            System.out.println("ENTER 4 TO EXIT");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE:");
            x=sc.nextInt();
            sc.nextLine();
            String tasks="";
            switch (x)
            {
                case 1:
                    System.out.print("Enter Name of title:");
                    String title=sc.nextLine();
                    String q="Select list from to_do_list where title='"+title+"' and user_id="+userId;
                    Statement st=con.createStatement();
                    ResultSet rs=st.executeQuery(q);
                    int count=1;
                    String list="";
                    if(rs.next())
                    {
                        list=rs.getString(1);
                    }
                    else
                    {
                        System.out.println("No such title found!");
                        break;
                    }
                    System.out.print("Enter the number of tasks you want to add:");
                    int n=sc.nextInt();
                    sc.nextLine();
                    for(int i=1;i<=n;i++)
                    {
                        System.out.println("Enter task " + count + ". :");
                        tasks=sc.nextLine();
                        obb.insertAtRear(tasks);
                        count++;
                    }
                    int c=list.split("\n").length + 1; ;
                    for(int i=1;i<=n;i++)
                    {
                        list+=c+". "+obb.deleteAtFront()+"\n";
                        c++;
                    }
                    con.setAutoCommit(false);
                    System.out.print("Do you really want to update the entry?(Yes/No):");
                    String ans=sc.next();
                    sc.nextLine();
                    if(ans.equalsIgnoreCase("yes"))
                    {
                        String q1="Update to_do_list set list=? where title='" + title + "' and user_id="+userId;
                        PreparedStatement pst=con.prepareStatement(q1);
                        pst.setString(1, list);
                        int n1=pst.executeUpdate();
                        if(n1>0)
                        {
                            System.out.println(title+" updated");
                        }
                        con.commit();
                    }
                    else
                    {
                        con.rollback();
                    }
                    break;
                case 2:
                    System.out.print("Enter the title of the list: ");
                    String delTitle = sc.nextLine();
                    String qDel = "SELECT list FROM to_do_list WHERE title=? and user_id=?";
                    PreparedStatement pstDel = con.prepareStatement(qDel);
                    pstDel.setString(1, delTitle); pstDel.setInt(2,userId);
                    ResultSet rsDel = pstDel.executeQuery();
                    if (!rsDel.next())
                    {
                        System.out.println("No such title found!");
                        break;
                    }
                    System.out.print("Enter the task you want to delete: ");
                    String delTask = sc.nextLine();
                    String[] tasksArr = rsDel.getString(1).split("\n");
                    List<String> updatedTasks = new ArrayList<>();
                    boolean found = false;
                    for (String task : tasksArr)
                    {
                        if (task.toLowerCase().contains(delTask.toLowerCase()))
                        {
                            found = true;
                            continue;
                        }
                        updatedTasks.add(task);
                    }
                    if (found==false)
                    {
                        System.out.println("Task not found!");
                        break;
                    }
                    String finalList = "";
                    for (int i = 0; i < updatedTasks.size(); i++)
                    {
                        String content = updatedTasks.get(i).split("\\. ", 2)[1];
                        finalList += (i + 1) + ". " + content + "\n";
                    }
                    con.setAutoCommit(false);
                    System.out.print("Confirm deletion? (Yes/No): ");
                    String anss=sc.next();
                    sc.nextLine();
                    if (anss.equalsIgnoreCase("yes"))
                    {
                        PreparedStatement pstUpdate = con.prepareStatement("UPDATE to_do_list SET list=? WHERE" +
                                " title=? and user_id=?");
                        pstUpdate.setString(1, finalList);
                        pstUpdate.setString(2, delTitle);
                        pstUpdate.setInt(3,userId);
                        pstUpdate.executeUpdate();
                        con.commit();
                        System.out.println("Task deleted and list updated!");
                    }
                    else
                    {
                        con.rollback();
                        System.out.println("Cancelled.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of list:");
                    String name=sc.nextLine();
                    String q2="Select list from to_do_list where title='"+name+"' and user_id="+userId;
                    Statement st2=con.createStatement();
                    ResultSet rs2=st2.executeQuery(q2);
                    String list2="";
                    if(rs2.next()==false)
                    {
                        System.out.println("No such title found");
                        break;
                    }
                    list2=rs2.getString(1);
                    System.out.print("Enter the name of task you want to change/rename:");
                    String task=sc.nextLine();
                    System.out.print("Enter the new task:");
                    String newTask=sc.nextLine();
                    if (list2.contains(task))
                    {
                        String updatedList = list2.replace(task, newTask);

                        String updateQ = "UPDATE to_do_list SET list=? WHERE title=? and user_id=?";
                        PreparedStatement pst2 = con.prepareStatement(updateQ);
                        pst2.setString(1, updatedList);
                        pst2.setString(2, name);
                        pst2.setInt(3,userId);
                        int rowsUpdated = pst2.executeUpdate();
                        if (rowsUpdated > 0)
                        {
                            System.out.println("Task renamed successfully!");
                        }
                    }
                    else
                    {
                        System.out.println("Task not found in the list!");
                    }
                    break;
                case 4:
                    System.out.println("Exiting from to-do list editing");
                    break;
                default:
                    System.out.println("Enter a valid choice!");
            }
        }while(x!=4);
    }

    static void searchList() throws Exception
    {
        System.out.print("Enter the title of list:");
        String title=sc.nextLine();
        String q="Select title,list from To_do_list where title=? and user_id=?";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setString(1,title);
        pst.setInt(2,userId);
        ResultSet rs=pst.executeQuery();
        if(rs.next())
        {
            System.out.println("Title:"+rs.getString(1));
            System.out.println("To-Do list:"+"\n"+rs.getString(2));
        }
        else
        {
            System.out.println("No such title found");
        }
    }

    static void deleteList() throws Exception
    {
        con.setAutoCommit(false);
        System.out.print("Enter the title of the to-do list:");
        String title=sc.nextLine();
        System.out.print("Are you sure, you want to delete the list?(Yes/No):");
        String ans=sc.next();
        sc.nextLine();
        if(ans.equalsIgnoreCase("yes"))
        {
            String q="Delete from to_do_list where title='"+title+"' and user_id="+userId;
            Statement st=con.createStatement();
            int n=st.executeUpdate(q);
            if (n>0)
            {
                System.out.println(title+" deleted!");
            }
            else
            {
                System.out.println("No such title found!");
            }
            con.commit();
        }
        else
        {
            System.out.println("Deletion aborted!");
            con.rollback();
        }
    }

    static void markAsDone() throws Exception
    {
        System.out.print("Enter the title of the to-do list: ");
        String title = sc.nextLine();
        String q = "SELECT list, completed_tasks FROM to_do_list WHERE title=? and user_id=?";
        PreparedStatement pst = con.prepareStatement(q);
        pst.setString(1, title); pst.setInt(2,userId);
        ResultSet rs = pst.executeQuery();
        if (!rs.next())
        {
            System.out.println("No such title found!");
            return;
        }
        System.out.print("Enter the completed task: ");
        String completed = sc.nextLine();
        String[] tasks = rs.getString("list").split("\n");
        String completedList = rs.getString("completed_tasks");
        String list = "", done = null;
        for (String t : tasks)
        {
            if (done == null && t.toLowerCase().contains(completed.toLowerCase()))
            {
                done = t;
            }
            else
            {
                list += t + "\n";
            }
        }
        if (done == null)
        {
            System.out.println("Task not found!");
            return;
        }
        if (completedList == null)
        {
            completedList = "";
        }
        completedList += done + "\n";
        System.out.print("Confirm mark as done? (Yes/No): ");
        String ans=sc.next();
        sc.nextLine();
        if (!ans.equalsIgnoreCase("yes"))
        {
            System.out.println("Cancelled.");
            return;
        }
        con.setAutoCommit(false);
        String update = "UPDATE to_do_list SET list=?, completed_tasks=? WHERE title=? and user_id=?";
        PreparedStatement pst2 = con.prepareStatement(update);
        pst2.setString(1, list);
        pst2.setString(2, completedList);
        pst2.setString(3, title); pst2.setInt(4,userId);
        pst2.executeUpdate();
        con.commit();
        System.out.println("Task marked as completed!");
        Quotes.displayRandomQuote();
    }
}
class Journal
{
    static Connection con;
    static int userId;
    static{
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Reflectoy", "root", "");
            if (con!=null)
            {
                //System.out.println("Connection done");
                String uid="{call getUserId(?,?,?)}";
                CallableStatement cst1=con.prepareCall(uid);
                cst1.setString(2,Reflecto.ob.userName);
                cst1.setString(3,Reflecto.ob.passCode);
                cst1.executeQuery();
                userId=cst1.getInt(1);
            }
        }
        catch (Exception e)
        {
            System.out.println("Connection Error");
        }
    }

    static void journalMenu() throws Exception
    {
        System.out.println();
        int x1;
        do
        {
            System.out.println();
            System.out.println("JOURNAL:");
            System.out.println("ENTER 1 TO ADD ENTRY");
            System.out.println("ENTER 2 TO EDIT ENTRY");
            System.out.println("ENTER 3 TO DELETE ENTRY");
            System.out.println("ENTER 4 TO SEARCH ENTRY");
            System.out.println("ENTER 5 TO EXIT");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE:");
            x1=sc.nextInt();
            sc.nextLine();

            switch(x1)
            {
                case 1:
                    System.out.println();
                    addEntry();
                    break;
                case 2:
                    System.out.println();
                    updateEntry();
                    break;
                case 3:
                    System.out.println();
                    deleteEntry();
                    break;
                case 4:
                    System.out.println();
                    searchEntry();
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Exiting from Journal!");
                    break;
                default:
                    System.out.println("Enter a valid choice");
            }
        }while(x1!=5);
    }

    static Scanner sc=new Scanner(System.in);
    static void addEntry() throws Exception
    {
        String date="{call getDate(?)}";
        CallableStatement cst=con.prepareCall(date);
        cst.executeQuery();
        System.out.println("Enter journal title:");
        String title=sc.nextLine();
        System.out.println("Enter your journal entry:");
        String entry=sc.nextLine();
        String insert="Insert into journal (user_id,title,journal_data,date_time) values(?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(insert);
        pst.setInt(1,userId);
        pst.setString(2,title);
        pst.setString(3,entry);
        pst.setTimestamp(4,cst.getTimestamp(1));
        int n=pst.executeUpdate();
        if(n>0)
        {
            System.out.println(n+" journal entry added successfully");
        }
        System.out.println();
        int n1;
        do {
            System.out.println();
            System.out.println("ENTER 1 TO IMPORT FILE");
            System.out.println("ENTER 2 TO EXPORT FILE");
            System.out.println("ENTER 3 TO EXIT");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE:");
            n1=sc.nextInt(); sc.nextLine();
            switch (n1)
            {
                case 1:
                    Journal.importFile(title);
                    break;
                case 2:
                    Journal.exportFile(title);
                    break;
                case 3:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Enter a valid choice");
            }
        }while(n1!=3);
    }

    static void deleteEntry() throws Exception
    {
        con.setAutoCommit(false);
        System.out.print("Enter the title of your entry:");
        String journalTitle=sc.nextLine();
        String delete="Delete from journal where title=? and user_id=?";
        PreparedStatement pst3=con.prepareStatement(delete);
        pst3.setString(1,journalTitle);
        pst3.setInt(2,userId);
        System.out.print("Do your really want to delete this entry?(Yes/No)");
        String response=sc.next(); sc.nextLine();
        if(response.equalsIgnoreCase("yes"))
        {
            System.out.println("Do you want to export the file?(Yes/No):");
            String ans1=sc.next(); sc.nextLine();
            if(ans1.equalsIgnoreCase("yes"))
            {
                Journal.exportFile(journalTitle);
            }
            int n3=pst3.executeUpdate();
            con.commit();
            if(n3>0)
            {
                System.out.println("Journal entry with title "+journalTitle+" deleted");
            }
            else
            {
                System.out.println("No such entry found!");
            }
        }
        else
        {
            con.rollback();
        }
    }

    static void updateEntry() throws Exception
    {
        int x;
        do
        {
            System.out.println();
            System.out.println("EDIT ENTRY:");
            System.out.println("ENTER 1 FOR ADDING DATA INTO THE JOURNAL ENTRY");
            System.out.println("ENTER 2 FOR DELETING DATA FROM THE JOURNAL ENTRY");
            System.out.println("ENTER 3 FOR REPLACING ONE WORD WITH ANOTHER IN THE JOURNAL ENTRY");
            System.out.println("ENTER 4 TO EXIT");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE:");
            x=sc.nextInt(); sc.nextLine();
            switch (x)
            {
                case 1:
                    String q="{call getJournalData(?,?,?)}";
                    CallableStatement cst=con.prepareCall(q);
                    System.out.println("Enter Journal Title:");
                    String title=sc.nextLine();
                    cst.setString(2,title);
                    cst.setInt(3,userId);
                    cst.executeQuery();
                    String entry=cst.getString(1);
                    if(entry==null)
                    {
                        System.out.println("No such title found");
                        break;
                    }
                    System.out.println("Enter the data you want to add:");
                    String updatedEntry=sc.nextLine();
                    con.setAutoCommit(false);
                    System.out.print("Do you really want to update the entry?(Yes/No):");
                    String ans=sc.next();
                    if(ans.equalsIgnoreCase("yes"))
                    {
                        String q1="Update journal set journal_data=? where title=? and user_id=?";
                        PreparedStatement pst=con.prepareStatement(q1);
                        pst.setString(1, (entry+" "+updatedEntry));
                        pst.setString(2, title); pst.setInt(3,userId);
                        int n=pst.executeUpdate();
                        if(n>0)
                        {
                            System.out.println(title+" updated");
                        }
                        con.commit();
                        System.out.println();
                    }
                    else
                    {
                        con.rollback();
                        System.out.println();
                    }
                    int n1;
                    do {
                        System.out.println();
                        System.out.println("ENTER 1 TO IMPORT FILE");
                        System.out.println("ENTER 2 TO EXPORT FILE");
                        System.out.println("ENTER 3 TO EXIT");
                        System.out.println();
                        System.out.print("ENTER YOUR CHOICE:");
                        n1=sc.nextInt(); sc.nextLine();
                        switch (n1)
                        {
                            case 1:
                                Journal.importFile(title);
                                break;
                            case 2:
                                Journal.exportFile(title);
                                break;
                            case 3:
                                System.out.println("Exiting");
                                break;
                            default:
                                System.out.println("Enter a valid choice");
                        }
                    }while(n1!=3);
                    break;
                case 2:
                    String fetchQ = "{call getJournalData(?,?,?)}";
                    CallableStatement call = con.prepareCall(fetchQ);
                    System.out.println("Enter Journal Title:");
                    String delTitle = sc.nextLine();
                    call.setString(2, delTitle); call.setInt(3,userId);
                    call.executeQuery();
                    String data = call.getString(1);
                    if(data==null)
                    {
                        System.out.println("No such title found");
                        break;
                    }
                    String[] lines = data.split("\\.");
                    System.out.println("Current Entry:");
                    for (int i = 0; i < lines.length; i++)
                    {
                        System.out.println((i + 1) + ". " + lines[i]+".");
                    }
                    System.out.print("Enter line number to delete: ");
                    int lineNo = sc.nextInt();
                    sc.nextLine();
                    if (lineNo < 1 || lineNo > lines.length)
                    {
                        System.out.println("Invalid line number!");
                        break;
                    }
                    String updatedData = "";
                    for (int i = 0; i < lines.length; i++)
                    {
                        if (i != (lineNo - 1))
                        {
                            updatedData += lines[i] + ".";
                        }
                    }
                    System.out.print("Confirm deletion? (Yes/No): ");
                    if (!sc.next().equalsIgnoreCase("yes"))
                    {
                        System.out.println("Cancelled.");
                        break;
                    }
                    con.setAutoCommit(false);
                    String updateQ = "UPDATE journal SET journal_data=? WHERE title=? and user_id=?";
                    PreparedStatement pstUpdate = con.prepareStatement(updateQ);
                    pstUpdate.setString(1, updatedData.trim());
                    pstUpdate.setString(2, delTitle);
                    pstUpdate.setInt(3,userId);
                    pstUpdate.executeUpdate();
                    con.commit();
                    System.out.println("Entry updated after deletion.");
                    break;
                case 3:
                    System.out.println("Enter title:");
                    String title3=sc.nextLine();
                    String q3="Select journal_data from journal where title=? and user_id=?";
                    PreparedStatement pst3=con.prepareStatement(q3);
                    pst3.setString(1,title3); pst3.setInt(2,userId);
                    ResultSet rs=pst3.executeQuery();
                    String [] entry3;
                    String updateEntry3="";
                    if(rs.next()==false)
                    {
                        System.out.println("No such title found");
                        break;
                    }
                    System.out.print("Enter the word you want to change:");
                    String word=sc.next();
                    System.out.print("Enter the word you want to replace with:"); sc.nextLine();
                    String update=sc.nextLine();
                    entry3=rs.getString(1).split(" ");
                    boolean flag=false;
                    for(int i=0;i<entry3.length;i++)
                    {
                        if(entry3[i].equalsIgnoreCase(word))
                        {
                            entry3[i]=update;
                            flag=true;
                        }
                        updateEntry3+=entry3[i]+" ";
                    }
                    if(flag==false)
                    {
                        System.out.println("No such word found!");
                        break;
                    }
                    String q31="Update journal set journal_data=? where title=? and user_id=?";
                    PreparedStatement pst31=con.prepareStatement(q31);
                    pst31.setString(1,updateEntry3); pst31.setString(2,title3);
                    pst31.setInt(3,userId);
                    int n3=pst31.executeUpdate();
                    if(n3>0)
                    {
                        System.out.println("Journal entry updated!");
                    }
                    int n31;
                    do {
                        System.out.println();
                        System.out.println("ENTER 1 TO IMPORT FILE");
                        System.out.println("ENTER 2 TO EXPORT FILE");
                        System.out.println("ENTER 3 TO EXIT");
                        System.out.println();
                        System.out.print("ENTER YOUR CHOICE:");
                        n31 =sc.nextInt(); sc.nextLine();
                        switch (n31)
                        {
                            case 1:
                                Journal.importFile(title3);
                                break;
                            case 2:
                                Journal.exportFile(title3);
                                break;
                            case 3:
                                System.out.println("Exiting");
                                break;
                            default:
                                System.out.println("Enter a valid choice");
                        }
                    }while(n31 !=3);
                    break;
                case 4:
                    System.out.println("Exiting!!!");
                    break;
                default:
                    System.out.println("Enter a valid choice");
            }
        }while(x!=4);
    }

    static void searchEntry() throws Exception
    {
        System.out.println("Enter the Title of your Journal Entry:");
        String title=sc.nextLine();
        String q="Select journal_data,date_time from journal where title=? and user_id=?";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setString(1,title); pst.setInt(2,userId);
        ResultSet rs=pst.executeQuery();
        if(rs.next()==true)
        {
            System.out.println("Title:"+title);
            System.out.println("Journal data:");
            System.out.println(rs.getString(1));
            System.out.println("Date and Time:"+rs.getTimestamp(2));
        }
        else
        {
            System.out.println("No such journal title found!");
        }
    }

    static void exportFile(String title) throws Exception
    {
        String q="Select * from journal where title=? and user_id=?";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setString(1,title); pst.setInt(2,userId);
        ResultSet rs=pst.executeQuery();
        if(rs.next())
        {
            BufferedWriter bw=new BufferedWriter(new FileWriter(title+".txt"));
            bw.write(title);
            bw.newLine();
            bw.write(String.valueOf(rs.getTimestamp(5)));
            bw.newLine();
            bw.write(rs.getString(3));
            bw.close();
        }
    }

    static void importFile(String title) throws Exception
    {
        System.out.print("Enter file name with path:");
        String file=sc.next();
        File f = new File(file);
        if (!f.exists())
        {
            System.out.println("File not found! Please check the path and try again.");
            return;
        }
        FileInputStream fis=new FileInputStream(file);
        String q = "UPDATE journal SET journal_files = ? WHERE title = ? and user_id=?";
        PreparedStatement pst = con.prepareStatement(q);
        pst.setBlob(1, fis);
        pst.setString(2, title); pst.setInt(3,userId);
        int n = pst.executeUpdate();
        if (n > 0)
        {
            System.out.println("File imported successfully!");
        }
    }
}

class Posts
{
    static Connection Connection() throws Exception
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reflectoy", "root", "");
        Scanner sc = new Scanner(System.in);
//        if (con != null) {
//            System.out.println("Connection done");
//        }
        return con;
    }

    static void postsMenu(int uid) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        boolean b=true;
        while(b)
        {
            System.out.println("\nPOSTS: ");
            System.out.println("ENTER 1: TO VIEW POSTS\nENTER 2: TO VIEW YOUR POSTS\nENTER 3: TO MAKE A NEW POST\nENTER 4: TO GO BACK");

            int ch=sc.nextInt();

            switch (ch)
            {
                case 1:
                    viewPosts();
                    break;
                case 2:
                    viewUserPosts(uid);
                    break;
                case 3:
                    addPost(uid);
                    break;
                case 4:
                    b=false;
                    break;
                default:
                    continue;
            }
        }
    }

    static void viewPosts() throws Exception
    {
        System.out.println();
        Scanner sc = new Scanner(System.in);

        boolean a=true;
        while(a) {
            System.out.println("VIEW POSTS:\nENTER 1: TO READ LATEST POST\nENTER 2: TO READ RANDOM POST\nENTER 3: TO GO BACK");
            int ch2 = sc.nextInt();

            switch (ch2)
            {
                case 1:
                    readLatestpost();
                    break;
                case 2:
                    readRandomPost();
                    break;
                case 3:
                    a = false;
                default:
                    continue;
            }
            System.out.println();
        }
        System.out.println();
    }

    static void readLatestpost() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        Connection con2= Connection();
        String q1="{call readLatestPost()}";

        CallableStatement cst1= con2.prepareCall(q1);

        ResultSet rs= cst1.executeQuery();

        int post_id=0;
        String post_usernm=null;

        while(rs.next())
        {
            post_id=rs.getInt(1);
            post_usernm=rs.getString(3);
            System.out.println("\nPOST: "+rs.getString(2)+"\nFIELD: "+rs.getString(5)+"\nLIKES: "+rs.getInt(4)+"\nPOSTED BY "+rs.getString(3));
        }

        System.out.println(post_id);
        System.out.print("\nDO YOU WANT TO LIKE THIS POST ? (YES/NO): ");
        String s1= sc.nextLine();

        if(s1.equalsIgnoreCase("yes"))
        {
            String q3="{call likePost(?)}";

            CallableStatement cst3= con2.prepareCall(q3);
            cst3.setInt(1,post_id);

            int n1= cst3.executeUpdate();

            if(n1>0)
            {
                System.out.println("YOU LIKED THE POST BY "+post_usernm);
            }
        }
        con2.close();
        System.out.println();
    }
    static void readRandomPost() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        Connection con2= Connection();

        con2.setAutoCommit(false);
        boolean f=true;

        while(f)
        {
            int maxPostId = 0;

            String q2 = "{call getNewPostId()}";
            CallableStatement cst2 = con2.prepareCall(q2);
            ResultSet rs = cst2.executeQuery();

            while (rs.next()) {
                maxPostId = rs.getInt(1);
            }

            int randomPostId= (int)(Math.random()*(maxPostId-101)) +100;
            System.out.println(randomPostId);

            String q1="{call readRandomPost(?)}";

            CallableStatement cst1= con2.prepareCall(q1);
            cst1.setInt(1,randomPostId);

            ResultSet rs1= cst1.executeQuery();

            String post_usernm=null;

            while (rs1.next())
            {
                System.out.println("\nPOST: "+rs1.getString(1)+"\nFIELD: "+rs1.getString(4)+"\nLIKES: "+rs1.getInt(3)+"\nPOSTED BY "+rs1.getString(2));
                post_usernm = rs1.getString(2);
            }
            if(post_usernm!=null)
            {
                con2.commit();
                f=false;
            }

            System.out.print("\nDO YOU WANT TO LIKE THIS POST ? (YES/NO): ");
            String s1= sc.nextLine();

            if(s1.equalsIgnoreCase("yes"))
            {
                String q3="{call likePost(?)}";

                CallableStatement cst3= con2.prepareCall(q3);
                cst3.setInt(1,randomPostId);

                int n1= cst3.executeUpdate();

                if(n1>0)
                {
                    System.out.println("YOU LIKED THE POST BY "+post_usernm);
                }
            }
        }

        con2.close();
        System.out.println();
    }

    static void viewUserPosts(int u_id) throws Exception
    {
        Connection con1= Connection();
        Scanner sc = new Scanner(System.in);

        String q1="call getUserPosts(?)";
        CallableStatement cst= con1.prepareCall(q1);
        cst.setInt(1,u_id);

        ResultSet rs= cst.executeQuery();

        int count=0;

        boolean a=true;
        while(a && rs.next())
        {
            count++;
            int post_id=rs.getInt(1);

            System.out.println();
            System.out.println("TITLE: "+rs.getString(2)+"\nFIELD: "+rs.getString(4)+"\nPOST: "+rs.getString(3)+"\nLIKES: "+rs.getInt(5));
            System.out.println();

            System.out.println("VIEW YOUR POSTS:");
            System.out.println("ENTER 1: TO VIEW NEXT POST\nENTER 2: TO DELETE THIS POST\nENTER 3: TO GO BACK");
            int ch=sc.nextInt();


            switch (ch)
            {
                case 1:
                {
                    if(rs.wasNull()==false)
                    {
                        System.out.println("YOU'VE VIEWED ALL YOUR POSTS");
                    }
                    //rs.next();
                }
                break;
                case 2:
                {
                    deleteUserPost(post_id);
                    //rs.next();
                }
                break;
                case 3:
                {
                    a = false;
                }
                break;
                default:
                {
                    System.out.println("INVALID CHOICE");
                }
            }
        }
        if(count==0)
        {
            System.out.println("YOU HAVE NO POSTS");
        }
        System.out.println();
    }

    static void deleteUserPost(int p_id) throws Exception
    {
        Connection con1= Connection();
        Scanner sc = new Scanner(System.in);

        String q1="call deleteUserPost(?)";
        CallableStatement cst= con1.prepareCall(q1);
        cst.setInt(1,p_id);

        int n= cst.executeUpdate();

        if(n>0)
        {
            System.out.println("POST DELETED SUCCESSFULLY");
        }
        System.out.println();
    }


    static void addPost(int user_id) throws Exception
    {
        Connection con1 = Connection();
        Scanner sc = new Scanner(System.in);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String q2 = "{call getNewPostId()}";
        CallableStatement cst2 = con1.prepareCall(q2);
        ResultSet rs = cst2.executeQuery();
        int new_post_id = 0;
        while (rs.next()) {
            new_post_id = rs.getInt(1);
        }

        System.out.print("ENTER TITLE FOR THE POST: ");
        String title = sc.nextLine();
        System.out.print("ENTER WHAT FIELD THIS POST BELONGS: ");
        String field = sc.nextLine();
        System.out.println("WRITE YOUR POST: ");
        String post_dt = sc.nextLine();

        String q3 = "{call addPost(?,?,?,?,?,?,?)}";
        CallableStatement cst3 = con1.prepareCall(q3);
        cst3.setInt(1, new_post_id);
        cst3.setInt(2, user_id);
        cst3.setString(3, title);
        cst3.setString(4, post_dt);
        cst3.setString(5, field);
        cst3.setInt(6,0);
        cst3.setTimestamp(7, timestamp);

        int n = cst3.executeUpdate();

        if (n > 0) {
            System.out.println("POST UPLOADED SUCCESSSFULLY");
        }
        con1.close();
        System.out.println();
    }
}
