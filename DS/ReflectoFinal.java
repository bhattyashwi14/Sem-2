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
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
            if (con!=null)
            {
                //System.out.println("Connection done");
            }
        }
        catch (Exception e)
        {
            System.out.println("\u001B[31mConnection Error\u001B[0m");
        }
    }
    static Scanner sc=new Scanner(System.in);
    static LoginSignup ob=new LoginSignup();
    public static void main(String[] args) throws Exception
    {
        ob.LoginSignup();
        int x;
        do {
            System.out.println();
            System.out.println("\u001B[36mMAIN MENU:\u001B[0m");
            System.out.println("\u001B[33mENTER 1 FOR JOURNAL\u001B[0m");
            System.out.println("\u001B[33mENTER 2 FOR TO-DO LIST\u001B[0m");
            System.out.println("\u001B[33mENTER 3 FOR CHALLENGES\u001B[0m");
            System.out.println("\u001B[33mENTER 4 FOR POSTS\u001B[0m");
            System.out.println("\u001B[33mENTER 5 FOR EXIT\u001B[0m");
            System.out.println();
            System.out.print("\u001B[35mENTER YOUR CHOICE:\u001B[0m");
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
                    Challenge c = new Challenge();
                    c.challengesMenu(ob);
                    break;
                case 4:
                    Posts p=new Posts();
                    p.postsMenu(ob.userId);
                    break;
                case 5:
                    System.out.println();
                    System.out.println("\u001B[34mExiting!!!\u001B[34m");
                    break;
                default:
                    System.out.println("\u001B[35mEnter valid choice\u001B[0m");
            }
        }while(x!=5);
    }
}

class LoginSignup
{
    String userName;
    String passCode;
    static int userId;
    Connection con;
    Quotes quotes=new Quotes();
    LoginSignup()
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
            if (con != null)
            {
                //System.out.println("Connection done");
            }
        }
        catch (Exception e)
        {
            System.out.println("\u001B[31mConnection Error\u001B[31m");
        }
    }
    void LoginSignup() throws Exception
    {
            Scanner sc=new Scanner(System.in);
            while(true)
            {
                System.out.println("\u001B[36mLogin/Signup:\u001B[0m");
                System.out.println("\u001B[33mEnter 1 for login\u001B[0m");
                System.out.println("\u001B[33mEnter 2 for signup\u001B[0m");
                System.out.println("\u001B[33mEnter 3 to exit\u001B[0m");
                System.out.print("\u001B[35mEnter your choice:\u001B[0m");
                int loginInput=sc.nextInt(); sc.nextLine();
                System.out.println();
                if (loginInput==2)
                {
                    while (true)
                    {
                        while (true)
                        {
                            System.out.print("Create UserName:");
                            userName = sc.next().toLowerCase();
                            try
                            {
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
                            }
                            catch (LengthException e)
                            {
                                System.out.println(e);
                            }
                            catch (EmptyDataException e)
                            {
                                System.out.println(e);
                            }
                        }
                        String getUserID = "{call getNewUserId()}";
                        CallableStatement cst = con.prepareCall(getUserID);
                        ResultSet rs = cst.executeQuery();
                        rs.next();
                        userId = rs.getInt(1);
                        System.out.println("\u001B[32mSigned in Successfully!!!\u001B[0m");
                        String insertUser = "{call insertUser(?,?,?)}";
                        CallableStatement cst1 = con.prepareCall(insertUser);
                        cst1.setInt(1, userId);
                        cst1.setString(2, userName);
                        cst1.setString(3, passCode);
                        cst1.executeUpdate();
                        break;
                    }
                }
                else if (loginInput==1)
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
                                    System.out.println("\u001B[32mLogged in successfully!\u001B[0m");
                                    quotes.displayRandomQuote();
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
                                    System.out.println("\u001B[31mThe passcode is incorrect for the entered username\u001B[0m");
                                }
                            }
                            break;
                        }
                        else
                        {
                            System.out.println("\u001B[31mNo such username found\u001B[0m");
                        }
                    }
                    break;
                }
                else if(loginInput==3)
                {
                    System.out.println("\u001B[34mExiting!!!\u001B[0m");
                    break;
                }
            }
    }
    static void signUpUserName(String userName)
    {
        if(userName.length()==0)
        {
            throw new EmptyDataException("\u001B[31mUser name can't be empty\u001B[0m");
        }
        if(userName.length()<4 || userName.length()>20)
        {
            throw new LengthException("\u001B[31mThe length of user name should be greater than 4 and less than 20\u001B[0m");
        }
        if(Character.isDigit(userName.charAt(0)))
        {
            throw new DigitException("\u001B[0mThe First character of user name should not be a digit\u001B[0m");
        }
    }

    static void signUpPassCode(String passCode)
    {
        if(passCode.length()==0)
        {
            throw new EmptyDataException("\u001B[31mPasscode can't be empty\u001B[0m");
        }
        if(passCode.length()<8)
        {
            throw new LengthException("\u001B[31mPasscode should be of minimum 8 character\u001B[0m");
        }
        if(passCode.length()>15)
        {
            throw new LengthException("\u001B[31mPasscode shouldn't be longer than 15 characters\u001B[0m");
        }
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
    Connection con;
    {
        try
        {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
            if (con!=null)
            {
                //System.out.println("Connection done");
            }
        }
        catch (Exception e)
        {
            System.out.println("\u001B[31mConnection Error\u001B[0m");
        }
    }
    void displayRandomQuote() throws Exception
    {
        String q = "SELECT quote FROM quotes ORDER BY RAND() LIMIT 5";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(q);
        SinglyLinkedList quoteList =new SinglyLinkedList();
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
        System.out.println("\u001B[34mQuote of the moment:\u001B[0m");
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
}

class To_Do_List extends LoginSignup
{
    Connection con=super.con;
    //static
    //{
        /*try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
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
        }*/
    //}
    Scanner sc=new Scanner(System.in);
    int count=0;
    Deque obb=new Deque(100);

    void todoListMenu() throws Exception
    {
        System.out.println();
        int x2;
        do {
            System.out.println();
            System.out.println("\u001B[36mTO-DO LIST:\u001B[0m");
            System.out.println("\u001B[33mENTER 1 TO CREATE TO-DO LIST\u001B[0m");
            System.out.println("\u001B[33mENTER 2 TO EDIT TO-DO LIST\u001B[0m");
            System.out.println("\u001B[33mENTER 3 TO DELETE TO-DO LIST\u001B[0m");
            System.out.println("\u001B[33mENTER 4 TO SEARCH/VIEW TO-DO LIST\u001B[0m");
            System.out.println("\u001B[33mENTER 5 TO MARK TASKS AS DONE\u001B[0m");
            System.out.println("\u001B[33mENTER 6 TO EXIT\u001B[0m");
            System.out.println();
            System.out.print("\u001B[35mENTER YOUR CHOICE:\u001B[0m");
            x2=sc.nextInt(); sc.nextLine();

            switch (x2)
            {
                case 1:
                    System.out.println();
                    createToDoList();
                    break;
                case 2:
                    System.out.println();
                    edit_list();
                    break;
                case 3:
                    System.out.println();
                    deleteList();
                    break;
                case 4:
                    System.out.println();
                    searchList();
                    break;
                case 5:
                    System.out.println();
                    markAsDone();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("\u001B[34mExiting from to-do list!\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[35mEnter a valid choice!\u001B[0m");
            }
        }while(x2!=6);
    }

    void createToDoList() throws Exception
    {
        System.out.print("\u001B[36mEnter title for to-do list:\u001B[0m");
        String name=sc.nextLine();
        System.out.println("1\u001B[35mWrite end when you're done adding tasks!\u001B[0m");
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
            System.out.println("\u001B[32m"+n+" to-do list added successfully!\u001B[0m");
        }
        super.quotes.displayRandomQuote();
    }

    void edit_list() throws Exception
    {
        int x;
        do {
            System.out.println("\u001B[36mEDIT TO-DO LIST:\u001B[0m");
            System.out.println("\u001B[33mENTER 1 TO ADD MORE TASKS\u001B[0m");
            System.out.println("\u001B[33mENTER 2 TO DELETE TASKS\u001B[0m");
            System.out.println("\u001B[33mENTER 3 TO CHANGE OR RENAME TASKS\u001B[0m");
            System.out.println("\u001B[33mENTER 4 TO EXIT\u001B[0m");
            System.out.println();
            System.out.print("\u001B[35mENTER YOUR CHOICE:\u001B[0m");
            x=sc.nextInt();
            sc.nextLine();
            String tasks="";
            switch (x)
            {
                case 1:
                    System.out.print("\u001B[36mEnter Name of title:\u001B[0m");
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
                        System.out.println("\u001B[31mNo such title found!\u001B[0m");
                        break;
                    }
                    System.out.print("\u001B[35mEnter the number of tasks you want to add:\u001B[0m");
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
                    System.out.print("\u001B[31mDo you really want to update the entry?(Yes/No):\u001B[0m");
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
                            System.out.println("\u001B[32m"+title+" updated\u001B[0m");
                        }
                        con.commit();
                    }
                    else
                    {
                        con.rollback();
                    }
                    break;
                case 2:
                    System.out.print("\u001B[36mEnter the title of the list: \u001B[0m");
                    String delTitle = sc.nextLine();
                    String qDel = "SELECT list FROM to_do_list WHERE title=? and user_id=?";
                    PreparedStatement pstDel = con.prepareStatement(qDel);
                    pstDel.setString(1, delTitle); pstDel.setInt(2,userId);
                    ResultSet rsDel = pstDel.executeQuery();
                    if (!rsDel.next())
                    {
                        System.out.println("\u001B[31mNo such title found!\u001B[0m");
                        break;
                    }
                    System.out.print("\u001B[36mEnter the task you want to delete: \u001B[0m");
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
                        System.out.println("\u001B[31mTask not found!\u001B[0m");
                        break;
                    }
                    String finalList = "";
                    for (int i = 0; i < updatedTasks.size(); i++)
                    {
                        String content = updatedTasks.get(i).split("\\. ", 2)[1];
                        finalList += (i + 1) + ". " + content + "\n";
                    }
                    con.setAutoCommit(false);
                    System.out.print("\u001B[31mConfirm deletion? (Yes/No): \u001B[0m");
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
                        System.out.println("\u001B[32mTask deleted and list updated!\u001B[0m");
                    }
                    else
                    {
                        con.rollback();
                        System.out.println("\u001B[31mCancelled.\u001B[0m");
                    }
                    break;
                case 3:
                    System.out.print("\u001B[36mEnter the title of list:\u001B[0m");
                    String name=sc.nextLine();
                    String q2="Select list from to_do_list where title='"+name+"' and user_id="+userId;
                    Statement st2=con.createStatement();
                    ResultSet rs2=st2.executeQuery(q2);
                    String list2="";
                    if(rs2.next()==false)
                    {
                        System.out.println("\u001B[31mNo such title found\u001B[0m");
                        break;
                    }
                    list2=rs2.getString(1);
                    System.out.print("\u001B[35mEnter the name of task you want to change/rename:\u001B[0m");
                    String task=sc.nextLine();
                    System.out.print("\u001B[36mEnter the new task:\u001B[0m");
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
                            System.out.println("\u001B[32mTask renamed successfully!\u001B[0m");
                        }
                    }
                    else
                    {
                        System.out.println("\u001B[31mTask not found in the list!\u001B[0m");
                    }
                    break;
                case 4:
                    System.out.println("\u001B[34mExiting from to-do list editing\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[35mEnter a valid choice!\u001B[0m");
            }
        }while(x!=4);
    }

    void searchList() throws Exception
    {
        System.out.print("\u001B[36mEnter the title of list:\u001B[0m");
        String title=sc.nextLine();
        String q="Select title,list from To_do_list where title=? and user_id=?";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setString(1,title);
        pst.setInt(2,userId);
        ResultSet rs=pst.executeQuery();
        if(rs.next())
        {
            System.out.println("\u001B[33mTitle:\u001B[0m"+rs.getString(1));
            System.out.println("\u001B[33mTo-Do list:\u001B[0m"+"\n"+rs.getString(2));
        }
        else
        {
            System.out.println("\u001B[31mNo such title found\u001B[0m");
        }
    }

    void deleteList() throws Exception
    {
        con.setAutoCommit(false);
        System.out.print("\u001B[36mEnter the title of the to-do list:\u001B[0m");
        String title=sc.nextLine();
        System.out.print("\u001B[31mAre you sure, you want to delete the list?(Yes/No):\u001B[0m");
        String ans=sc.next();
        sc.nextLine();
        if(ans.equalsIgnoreCase("yes"))
        {
            String q="Delete from to_do_list where title='"+title+"' and user_id="+userId;
            Statement st=con.createStatement();
            int n=st.executeUpdate(q);
            if (n>0)
            {
                System.out.println("\u001B[32m"+title+" deleted!\u001B[0m");
            }
            else
            {
                System.out.println("\u001B[31mNo such title found!\u001B[0m");
            }
            con.commit();
        }
        else
        {
            System.out.println("\u001B[31mDeletion aborted!\u001B[0m");
            con.rollback();
        }
    }

    void markAsDone() throws Exception
    {
        System.out.print("\u001B[36mEnter the title of the to-do list: \u001B[0m");
        String title = sc.nextLine();
        String q = "SELECT list, completed_tasks FROM to_do_list WHERE title=? and user_id=?";
        PreparedStatement pst = con.prepareStatement(q);
        pst.setString(1, title); pst.setInt(2,userId);
        ResultSet rs = pst.executeQuery();
        if (!rs.next())
        {
            System.out.println("\u001B[31mNo such title found!\u001B[0m");
            return;
        }
        System.out.print("\u001B[35mEnter the completed task: \u001B[0m");
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
            System.out.println("\u001B[31mTask not found!\u001B[0m");
            return;
        }
        if (completedList == null)
        {
            completedList = "";
        }
        completedList += done + "\n";
        System.out.print("\u001B[31mConfirm mark as done? (Yes/No): \u001B[0m");
        String ans=sc.next();
        sc.nextLine();
        if (!ans.equalsIgnoreCase("yes"))
        {
            System.out.println("\u001B[31mCancelled.\u001B[0m");
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
        System.out.println("\u001B[32mTask marked as completed!\u001B[0m");
        super.quotes.displayRandomQuote();
    }
}
class Journal extends LoginSignup
{
    Connection con=super.con;
    /*static{
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
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
    }*/
    Scanner sc=new Scanner(System.in);
    void journalMenu() throws Exception
    {
        System.out.println();
        int x1;
        do
        {
            System.out.println();
            System.out.println("\u001B[36mJOURNAL:\u001B[0m");
            System.out.println("\u001B[33mENTER 1 TO ADD ENTRY\u001B[0m");
            System.out.println("\u001B[33mENTER 2 TO EDIT ENTRY\u001B[0m");
            System.out.println("\u001B[33mENTER 3 TO DELETE ENTRY\u001B[0m");
            System.out.println("\u001B[33mENTER 4 TO SEARCH ENTRY\u001B[0m");
            System.out.println("\u001B[33mENTER 5 TO EXIT\u001B[0m");
            System.out.println();
            System.out.print("\u001B[35mENTER YOUR CHOICE:\u001B[0m");
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
                    System.out.println("\u001B[34mExiting from Journal!\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[35mEnter a valid choice\u001B[0m");
            }
        }while(x1!=5);
    }

    void addEntry() throws Exception
    {
        String date="{call getDate(?)}";
        CallableStatement cst=con.prepareCall(date);
        cst.executeQuery();
        System.out.println("\u001B[36mEnter journal title:\u001B[0m");
        String title=sc.nextLine();
        System.out.println("\u001B[36mEnter your journal entry:\u001B[0m");
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
            System.out.println("\u001B[32m"+n+" journal entry added successfully\u001B[0m");
        }
        System.out.println();
        int n1;
        do {
            System.out.println();
            System.out.println("\u001B[36mFILE:\u001B[0m");
            System.out.println("\u001B[33mENTER 1 TO IMPORT FILE\u001B[0m");
            System.out.println("\u001B[33mENTER 2 TO EXPORT FILE\u001B[0m");
            System.out.println("\u001B[33mENTER 3 TO EXIT\u001B[0m");
            System.out.println();
            System.out.print("\u001B[35mENTER YOUR CHOICE:\u001B[0m");
            n1=sc.nextInt(); sc.nextLine();
            switch (n1)
            {
                case 1:
                    importFile(title);
                    break;
                case 2:
                    exportFile(title);
                    break;
                case 3:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Enter a valid choice");
            }
        }while(n1!=3);
    }

    void deleteEntry() throws Exception
    {
        con.setAutoCommit(false);
        System.out.print("\u001B[36mEnter the title of your entry:\u001B[0m");
        String journalTitle=sc.nextLine();
        String delete="Delete from journal where title=? and user_id=?";
        PreparedStatement pst3=con.prepareStatement(delete);
        pst3.setString(1,journalTitle);
        pst3.setInt(2,userId);
        System.out.print("\u001B[31mDo your really want to delete this entry?(Yes/No)\u001B[0m");
        String response=sc.next(); sc.nextLine();
        if(response.equalsIgnoreCase("yes"))
        {
            System.out.println("\u001B[35mDo you want to export the file?(Yes/No):\u001B[0m");
            String ans1=sc.next(); sc.nextLine();
            if(ans1.equalsIgnoreCase("yes"))
            {
                exportFile(journalTitle);
            }
            int n3=pst3.executeUpdate();
            con.commit();
            if(n3>0)
            {
                System.out.println("\u001B[32mJournal entry with title "+journalTitle+" deleted\u001B[0m");
            }
            else
            {
                System.out.println("\u001B[31mNo such entry found!\u001B[0m");
            }
        }
        else
        {
            con.rollback();
        }
    }

    void updateEntry() throws Exception
    {
        int x;
        do
        {
            System.out.println();
            System.out.println("\u001B[36mEDIT ENTRY:\u001B[0m");
            System.out.println("\u001B[33mENTER 1 FOR ADDING DATA INTO THE JOURNAL ENTRY\u001B[0m");
            System.out.println("\u001B[33mENTER 2 FOR DELETING DATA FROM THE JOURNAL ENTRY\u001B[0m");
            System.out.println("\u001B[33mENTER 3 FOR REPLACING ONE WORD WITH ANOTHER IN THE JOURNAL ENTRY\u001B[0m");
            System.out.println("\u001B[33mENTER 4 TO EXIT\u001B[0m");
            System.out.println();
            System.out.print("\u001B[35mENTER YOUR CHOICE:\u001B[0m");
            x=sc.nextInt(); sc.nextLine();
            switch (x)
            {
                case 1:
                    String q="{call getJournalData(?,?,?)}";
                    CallableStatement cst=con.prepareCall(q);
                    System.out.println("\u001B[36mEnter Journal Title:\u001B[0m");
                    String title=sc.nextLine();
                    cst.setString(2,title);
                    cst.setInt(3,userId);
                    cst.executeQuery();
                    String entry=cst.getString(1);
                    if(entry==null)
                    {
                        System.out.println("\u001B[31mNo such title found\u001B[0m");
                        break;
                    }
                    System.out.println("\u001B[36mEnter the data you want to add:\u001B[0m");
                    String updatedEntry=sc.nextLine();
                    con.setAutoCommit(false);
                    System.out.print("\u001B[31mDo you really want to update the entry?(Yes/No):\u001B[0m");
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
                            System.out.println("\u001B[32m"+title+" updated\u001B[0m");
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
                        System.out.println("\u001B[36mFILE:\u001B[0m");
                        System.out.println("\u001B[33mENTER 1 TO IMPORT FILE\u001B[0m");
                        System.out.println("\u001B[33mENTER 2 TO EXPORT FILE\u001B[0m");
                        System.out.println("\u001B[33mENTER 3 TO EXIT\u001B[0m");
                        System.out.println();
                        System.out.print("\u001B[35mENTER YOUR CHOICE:\u001B[0m");
                        n1=sc.nextInt(); sc.nextLine();
                        switch (n1)
                        {
                            case 1:
                                importFile(title);
                                break;
                            case 2:
                                exportFile(title);
                                break;
                            case 3:
                                System.out.println("\u001B[34mExiting\u001B[0m");
                                break;
                            default:
                                System.out.println("\u001B[35mEnter a valid choice\u001B[0m");
                        }
                    }while(n1!=3);
                    break;
                case 2:
                    String fetchQ = "{call getJournalData(?,?,?)}";
                    CallableStatement call = con.prepareCall(fetchQ);
                    System.out.println("\u001B[36mEnter Journal Title:\u001B[0m");
                    String delTitle = sc.nextLine();
                    call.setString(2, delTitle); call.setInt(3,userId);
                    call.executeQuery();
                    String data = call.getString(1);
                    if(data==null)
                    {
                        System.out.println("\u001B[31mNo such title found\u001B[0m");
                        break;
                    }
                    String[] lines = data.split("\\.");
                    System.out.println("\u001B[33mCurrent Entry:\u001B[0m");
                    for (int i = 0; i < lines.length; i++)
                    {
                        System.out.println((i + 1) + ". " + lines[i]+".");
                    }
                    System.out.print("\u001B[36mEnter line number to delete: \u001B[36m");
                    int lineNo = sc.nextInt();
                    sc.nextLine();
                    if (lineNo < 1 || lineNo > lines.length)
                    {
                        System.out.println("\u001B[31mInvalid line number!\u001B[0m");
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
                    System.out.print("\u001B[31mConfirm deletion? (Yes/No): \u001B[0m");
                    if (!sc.next().equalsIgnoreCase("yes"))
                    {
                        System.out.println("\u001B[31mCancelled.\u001B[0m");
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
                    System.out.println("\u001B[32mEntry updated after deletion.\u001B[0m");
                    break;
                case 3:
                    System.out.println("\u001B[36mEnter title:\u001B[0m");
                    String title3=sc.nextLine();
                    String q3="Select journal_data from journal where title=? and user_id=?";
                    PreparedStatement pst3=con.prepareStatement(q3);
                    pst3.setString(1,title3); pst3.setInt(2,userId);
                    ResultSet rs=pst3.executeQuery();
                    String [] entry3;
                    String updateEntry3="";
                    if(rs.next()==false)
                    {
                        System.out.println("\u001B[31mNo such title found\u001B[0m");
                        break;
                    }
                    System.out.print("\u001B[35mEnter the word you want to change:\u001B[0m");
                    String word=sc.next();
                    System.out.print("\u001B[36mEnter the word you want to replace with:\u001B[0m"); sc.nextLine();
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
                        System.out.println("\u001B[31mNo such word found!\u001B[0m");
                        break;
                    }
                    String q31="Update journal set journal_data=? where title=? and user_id=?";
                    PreparedStatement pst31=con.prepareStatement(q31);
                    pst31.setString(1,updateEntry3); pst31.setString(2,title3);
                    pst31.setInt(3,userId);
                    int n3=pst31.executeUpdate();
                    if(n3>0)
                    {
                        System.out.println("\u001B[32mJournal entry updated!\u001B[0m");
                    }
                    int n31;
                    do {
                        System.out.println();
                        System.out.println("\u001B[36mFILE:\u001B[0m");
                        System.out.println("\u001B[33mENTER 1 TO IMPORT FILE\u001B[0m");
                        System.out.println("\u001B[33mENTER 2 TO EXPORT FILE\u001B[0m");
                        System.out.println("\u001B[33mENTER 3 TO EXIT\u001B[0m");
                        System.out.println();
                        System.out.print("\u001B[35mENTER YOUR CHOICE:\u001B[0m");
                        n31 =sc.nextInt(); sc.nextLine();
                        switch (n31)
                        {
                            case 1:
                                importFile(title3);
                                break;
                            case 2:
                                exportFile(title3);
                                break;
                            case 3:
                                System.out.println("\u001B[34mExiting!\u001B[0m");
                                break;
                            default:
                                System.out.println("\u001B[35mEnter a valid choice\u001B[35m");
                        }
                    }while(n31 !=3);
                    break;
                case 4:
                    System.out.println("\u001B[34mExiting Journal Editing!!!\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[35mEnter a valid choice\u001B[0m");
            }
        }while(x!=4);
    }

    void searchEntry() throws Exception
    {
        System.out.println("\u001B[36mEnter the Title of your Journal Entry:\u001B[0m");
        String title=sc.nextLine();
        String q="Select journal_data,date_time from journal where title=? and user_id=?";
        PreparedStatement pst=con.prepareStatement(q);
        pst.setString(1,title); pst.setInt(2,userId);
        ResultSet rs=pst.executeQuery();
        if(rs.next()==true)
        {
            System.out.println("\u001B[33mTitle:\u001B[om"+title);
            System.out.println("\u001B[33mJournal data:\u001B[0m");
            System.out.println(rs.getString(1));
            System.out.println("\u001B[33mDate and Time:\u001B[0m"+rs.getTimestamp(2));
        }
        else
        {
            System.out.println("\u001B[31mNo such journal title found!\u001B[0m");
        }
    }

    void exportFile(String title) throws Exception
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
        System.out.println("\u001B[32mFile exported successfully!\u001B[0m");
    }

    void importFile(String title) throws Exception
    {
        System.out.print("\u001B[36mEnter file name with path:\u001B[0m");
        String file=sc.next();
        File f = new File(file);
        if (!f.exists())
        {
            System.out.println("\u001B[31mFile not found! Please check the path and try again.\u001B[0m");
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
            System.out.println("\u001B[32mFile imported successfully!\u001B[0m");
        }
    }
}

class Posts extends LoginSignup
{
    Connection con=super.con;
    Scanner sc = new Scanner(System.in);
    /*static Connection Connection() throws Exception
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
        Scanner sc = new Scanner(System.in);
//        if (con != null) {
//            System.out.println("Connection done");
//        }
        return con;
    }*/

    void postsMenu(int uid) throws Exception
    {
        boolean b=true;
        while(b)
        {
            System.out.println("\u001B[36m\nPOSTS: \u001B[0m");
            System.out.println("\u001B[33mENTER 1: TO VIEW POSTS\nENTER 2: TO VIEW YOUR POSTS\nENTER 3: TO MAKE A NEW POST\nENTER 4: TO GO BACK\u001B[0m");
            System.out.println("\u001B[35mENTER YOUR CHOICE:\u001B[0m");
            int ch=sc.nextInt(); sc.nextLine();

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

    void viewPosts() throws Exception
    {
        System.out.println();

        boolean a=true;
        while(a) {
            System.out.println("\u001B[36mVIEW POSTS:\u001B[0m");
            System.out.println("\u001B[33mENTER 1: TO READ LATEST POST\nENTER 2: TO READ RANDOM POST\nENTER 3: TO GO BACK\u001B[0m");
            int ch2 = sc.nextInt(); sc.nextLine();

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
        }
        System.out.println();
    }

    void readLatestpost() throws Exception
    {
        String q1="{call readLatestPost()}";

        CallableStatement cst1= con.prepareCall(q1);

        ResultSet rs= cst1.executeQuery();

        int post_id=0;
        String post_usernm=null;

        while(rs.next())
        {
            post_id=rs.getInt(1);
            post_usernm=rs.getString(3);
            System.out.println("\u001B[33m\nPOST: \u001B[0m"+rs.getString(2)+"\n\u001B[33mFIELD: \u001B[0m"+rs.getString(5)+"\n\u001B[33mLIKES: \u001B[0m"+rs.getInt(4)+"\n\u001B[33mPOSTED BY: \u001B[0m"+rs.getString(3));
        }

        //System.out.println(post_id);
        System.out.println("\u001B[35mDO YOU WANT TO LIKE THIS POST ? (YES/NO): \u001B[0m");
        String s1= sc.nextLine();

        if(s1.equalsIgnoreCase("yes"))
        {
            String q3="{call likePost(?)}";

            CallableStatement cst3= con.prepareCall(q3);
            cst3.setInt(1,post_id);

            int n1= cst3.executeUpdate();

            if(n1>0)
            {
                System.out.println("\u001B[32mYOU LIKED THE POST BY \u001B[0m"+post_usernm);
            }
        }
        con.close();
        System.out.println();
    }

    void readRandomPost() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        //Connection con2= Connection();

        con.setAutoCommit(false);
        boolean f=true;

        while(f)
        {
            int maxPostId = 0;

            String q2 = "{call getNewPostId()}";
            CallableStatement cst2 = con.prepareCall(q2);
            ResultSet rs = cst2.executeQuery();

            while (rs.next()) {
                maxPostId = rs.getInt(1);
            }

            int randomPostId= (int)(Math.random()*(maxPostId-101)) +100;
            //System.out.println(randomPostId);

            String q1="{call readRandomPost(?)}";

            CallableStatement cst1= con.prepareCall(q1);
            cst1.setInt(1,randomPostId);

            ResultSet rs1= cst1.executeQuery();

            String post_usernm=null;

            while (rs1.next())
            {
                System.out.println("\u001B[33m\nPOST: \u001B[0m"+rs1.getString(1)+"\n\u001B[33mFIELD: \u001B[0m"+rs1.getString(4)+"\n\u001B[31mLIKES: \u001B[0m"+rs1.getInt(3)+"\n\u001B[33mPOSTED BY :\u001B[0m"+rs1.getString(2));
                post_usernm = rs1.getString(2);
            }
            if(post_usernm!=null)
            {
                con.commit();
                f=false;
            }

            System.out.println("\u001B[35mDO YOU WANT TO LIKE THIS POST ? (YES/NO): \u001B[0m");
            String s1= sc.nextLine();

            if(s1.equalsIgnoreCase("yes"))
            {
                String q3="{call likePost(?)}";

                CallableStatement cst3= con.prepareCall(q3);
                cst3.setInt(1,randomPostId);

                int n1= cst3.executeUpdate();

                if(n1>0)
                {
                    System.out.println("\u001B[32mYOU LIKED THE POST BY \u001B[0m"+post_usernm);
                }
            }
        }
        con.close();
        System.out.println();
    }

    void viewUserPosts(int u_id) throws Exception
    {

        String q1="call getUserPosts(?)";
        CallableStatement cst= con.prepareCall(q1);
        cst.setInt(1,u_id);

        ResultSet rs= cst.executeQuery();

        int count=0;

        boolean a=true;
        while(a && rs.next())
        {
            count++;
            int post_id=rs.getInt(1);

            System.out.println();
            System.out.println("\u001B[33mTITLE: \u001B[0m"+rs.getString(2)+"\n\u001B[33mFIELD: \u001B[0m"+rs.getString(4)+"\n\u001B[33mPOST: \u001B[0m"+rs.getString(3)+"\n\u001B[33mLIKES: \u001B[0m"+rs.getInt(5));
            System.out.println();

            System.out.println("\u001B[36mVIEW YOUR POSTS:\u001B[36m");
            System.out.println("\u001B[33mENTER 1: TO VIEW NEXT POST\nENTER 2: TO DELETE THIS POST\nENTER 3: TO GO BACK\u001B[0m");
            int ch=sc.nextInt();

            switch (ch)
            {
                case 1:
                {
                    if(rs.wasNull()==false)
                    {
                        System.out.println("\u001B[35mYOU'VE VIEWED ALL YOUR POSTS\u001B[0m");
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
                    System.out.println("\u001B[31mINVALID CHOICE\u001B[0m");
                }
            }
        }
        if(count==0)
        {
            System.out.println("\u001B[35mYOU HAVE NO POSTS\u001B[0m");
        }
        System.out.println();
        con.close();
    }

    void deleteUserPost(int p_id) throws Exception
    {
        String q1="call deleteUserPost(?)";
        CallableStatement cst= con.prepareCall(q1);
        cst.setInt(1,p_id);

        int n= cst.executeUpdate();

        if(n>0)
        {
            System.out.println("\u001B[32mPOST DELETED SUCCESSFULLY\u001B[0m");
        }
        System.out.println();
        con.close();
    }

    void addPost(int user_id) throws Exception
    {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String q2 = "{call getNewPostId()}";
        CallableStatement cst2 = con.prepareCall(q2);
        ResultSet rs = cst2.executeQuery();
        int new_post_id = 0;
        while (rs.next()) {
            new_post_id = rs.getInt(1);
        }

        System.out.print("\u001B[36mENTER TITLE FOR THE POST: \u001B[0m");
        String title = sc.nextLine();
        System.out.print("\u001B[36mENTER WHAT FIELD THIS POST BELONGS: \u001B[0m");
        String field = sc.nextLine();
        System.out.println("\u001B[35mWRITE YOUR POST: \u001B[0m");
        String post_dt = sc.nextLine();

        String q3 = "{call addPost(?,?,?,?,?,?,?)}";
        CallableStatement cst3 = con.prepareCall(q3);
        cst3.setInt(1, new_post_id);
        cst3.setInt(2, user_id);
        cst3.setString(3, title);
        cst3.setString(4, post_dt);
        cst3.setString(5, field);
        cst3.setInt(6,0);
        cst3.setTimestamp(7, timestamp);

        int n = cst3.executeUpdate();

        if (n > 0) {
            System.out.println("\u001B[32mPOST UPLOADED SUCCESSSFULLY\u001B[0m");
        }
        con.close();
        System.out.println();
    }
}
class Challenge extends LoginSignup
{

    Scanner sc = new Scanner(System.in);
    Connection con=super.con;

    /*{
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    LoginSignup currentUser;


    public void challengesMenu(LoginSignup o) throws Exception {
        boolean exitMyChallenges = false;
        while (!exitMyChallenges) {
            exitMyChallenges = exitChallenges(o);
        }
    }

    public boolean exitChallenges(LoginSignup object) throws Exception {          // exitChallenges(CurrentUserDetails object)

        currentUser = object;
        boolean exitTemp = false;

        System.out.println();
        System.out.println("\u001B[36mCHALLENGES:\u001B[0m");
        System.out.println("\u001B[33m1. BROWSE ALL CHALLENGES\u001B[0m");
        System.out.println("\u001B[33m2. ADD A NEW CHALLENGE\u001B[0m");
        System.out.println("\u001B[33m3. MY CHALLENGES\u001B[0m");
        System.out.println("\u001B[33m4. MY ACCOMPLISHMENTS\u001B[0m");
        System.out.println("\u001B[33m5. BACK\u001B[0m");
        System.out.println("\u001B[35mENTER YOUR CHOICE:\u001B[0m");

        //        System.out.println("2. BROWSE BY USER ID");
//        System.out.println("3. BROWSE BY USERNAME");

        int choice1 = sc.nextInt();

        switch (choice1) {

            case 1:
                AllChallenges ob1 = new AllChallenges();
                ob1.browseAllChallenges();
                break;

//            case 2:
////                UserIdChallenges ob2 = new UserIdChallenges();
////                System.out.println("ENTER -1 TO EXIT.");
////                int userIdBrowsing = 0;
////                while(userIdBrowsing!=-1) {
////                    System.out.println("ENTER USER ID:");
////                    userIdBrowsing = sc.nextInt();
////                    ob2.browseChallengesByUserID(userIdBrowsing);
////                }
//                break;
//
//            case 3:
//                UserNameChallenges ob3 = new UserNameChallenges();
//                System.out.println("ENTER EMPTY STRING TO EXIT.");
//                String userNameBrowsing = "username";
//                while(!userNameBrowsing.isEmpty()) {
//                    System.out.println("ENTER USERNAME:");
//                    userNameBrowsing = sc.nextLine();
//                    ob3.browseChallengesByUserID(userNameBrowsing);
//                }
//                break;

            case 2:
                AddChallenge ob4 = new AddChallenge();
                ob4.addChallenge(currentUser);
                break;

            case 3:
                MyChallenges ob5 = new MyChallenges();
                ob5.myChallengesSection(currentUser);
                break;

            case 4:
                MyAccomplishments ob6 = new MyAccomplishments();
                ob6.browseAccomplishments(currentUser);
                break;

            case 5:
                exitTemp = true;
                break;

            default:
                // displayDefaultSwitchMessage();
        }

        return exitTemp;

    }
}

class Challenges {

    int userID, cID;
    String challengeDesc, accepted, accomplished;

    Challenges(int userID, int cID, String challengeDesc, String accepted, String accomplished){
        this.userID = userID;
        this.cID = cID;
        this.challengeDesc = challengeDesc;
        this.accepted = accepted;
        this.accomplished = accomplished;
    }

    @Override
    public String toString() {
        return "Challenges{" +
                "userID=" + userID +
                ", cID=" + cID +
                ", challengeDesc='" + challengeDesc + '\'' +
                ", accepted='" + accepted + '\'' +
                ", accomplished='" + accomplished + '\'' +
                '}';
    }
}
class ChallengesStack {

    int cap, top;
    Challenges[] s;

    ChallengesStack(int size){
        this.cap = size;
        this.top = -1;
        this.s = new Challenges[size];
    }

    void push(Challenges ob){
        if(top >= cap-1){
            System.out.println("Overflow");
        }else{
            top++;
            s[top]=ob;
        }
    }

    Challenges peep(int i)
    {
        if (top == -1) return null;  // empty stack
        if(i < 0 || i > top){
            System.out.println("Invalid index: " + i);
            return null;
        }
        return s[i];
    }

    Challenges peek() {
        if (top == -1) {
            System.out.println("Stack Empty.");
            return null;
        } else {
            return s[top];
        }
    }

    Challenges pop(){
        if(top == -1){
            System.out.println("Underflow");
            return null;
        }else {
            return s[top--];
        }
    }

    void display(){
        if(top == -1){
            System.out.println("Stack Empty.");
        }else{
            for(int i = top; i >= 0; i--){
                System.out.println(s[i]);
            }
        }
    }

}
class AllChallenges {
    Scanner sc = new Scanner(System.in);
    Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int totalChallenges() throws SQLException {
        int returnValue = 0;
        String q1 = "{call getChallengesCount()}";
        CallableStatement cst1 = con.prepareCall(q1);
        ResultSet rs1 = cst1.executeQuery();
        while (rs1.next()) {
            returnValue = rs1.getInt(1);
        }
        return returnValue;
    }

    public ChallengesStack createAllChallengesStack(int size) throws SQLException {
        ChallengesStack returnVariable = new ChallengesStack(size);
        String q1 = "{call getAllChallenges()}";
        CallableStatement cst1 = con.prepareCall(q1);
        ResultSet rs1 = cst1.executeQuery();
        while (rs1.next()) {
            returnVariable.push(new Challenges(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
        }
        return returnVariable;
    }

    public void browseAllChallenges() throws SQLException {

        int totalEntries = totalChallenges();
        if (totalEntries == 0) {
            System.out.println("No challenges found.");
            return;
        }
        ChallengesStack mainStack = createAllChallengesStack(totalEntries);
        boolean exit = false;
        int userInput;
        int indexTracker = 0; //Can put (int)(Math.random()* (mainStack.cap))+1 to start from random Challenge.
        do {
            // Persistent Browsing State: Save current stack state into DB, so when the user logs back in, they resume where they left off.
            Challenges temp = mainStack.peep(indexTracker);
            System.out.println("Challenge Description : "+temp.challengeDesc);
            System.out.println("Accepted : "+temp.accepted);
            System.out.println("Accomplished : "+temp.accomplished);
            System.out.println();
            System.out.println("1. PREV");
            System.out.println("2. ACCEPT CHALLENGE");
            System.out.println("3. NEXT");
            System.out.println("4. RANDOM");
            System.out.println("5. BACK");
            userInput = sc.nextInt();
            switch (userInput) {
                case 1:
                    if (indexTracker <= 1) {
                        indexTracker = mainStack.cap - 1;     // Circular Queue Behaviour.
                    } else {
                        indexTracker--;
                    }
                    break;
//                    int n = mainStack.top + 1;  // total number of items
//
//// PREV
//                    indexTracker = (indexTracker - 1 + n) % n;
//
//// NEXT
//                    indexTracker = (indexTracker + 1) % n;
//
//// RANDOM
//                    indexTracker = (int)(Math.random() * n);
//                    break;

                case 2:
                    if (temp.accepted.equals("No")) {
                        String q1 = "UPDATE challenges_table SET accepted='Yes' WHERE challenge_ID=?";
                        PreparedStatement pst1 = con.prepareStatement(q1);
                        pst1.setInt(1,temp.cID);
                        int r = pst1.executeUpdate();
                        if(r>0) {
                            temp.accepted = "Yes";
                            System.out.println("CHALLENGE ACCEPTED.");
                        }else{
                            System.out.println("COULD NOT ACCEPT THE CHALLENGE. PLEASE TRY AGAIN.");
                        }
                    } else if(temp.accepted.equals("Yes")) {
                        System.out.println("CHALLENGE ALREADY IN PROGRESS.");
                    } else if(temp.accepted.equals("Done")){
                        System.out.println("ALREADY ACCOMPLISHED");
                    }
                    break;

                case 3:
                    if (indexTracker >= mainStack.cap - 1) {
                        indexTracker = 0;
                    } else {
                        indexTracker++;
                    }
                    break;
//                    int n3 = mainStack.top + 1;  // total number of items
//
//// PREV
//                    indexTracker = (indexTracker - 1 + n3) % n3;
//
//// NEXT
//                    indexTracker = (indexTracker + 1) % n3;
//
//// RANDOM
//                    indexTracker = (int)(Math.random() * n3);
//                    break;

                case 4:
                    indexTracker = (int) (Math.random() * (mainStack.cap)) + 1;
                    break;

                case 5:
                    exit = true;
            }

        } while (!exit);

    }
}
class UserIdChallenges {
    Scanner sc = new Scanner(System.in);
    Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int userIdTotalChallenges(int userID) throws SQLException{
        int returnValue = 0;
        String q1 = "{call getChallengesCountByUserID(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1,userID);
        ResultSet rs1 = cst1.executeQuery();
        while (rs1.next()) {
            returnValue = rs1.getInt(1);
        }
        return returnValue;
    }

    public ChallengesStack createUserIdChallengesStack(int size, int userID) throws SQLException{
        ChallengesStack returnVariable = new ChallengesStack(size);
        String q1 = "{call getAllChallengesByUserID(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1,userID);
        ResultSet rs1 = cst1.executeQuery();
        while (rs1.next()) {
            returnVariable.push(new Challenges(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
        }
        return returnVariable;
    }


    public void browseChallengesByUserID(int userID) throws SQLException {
        int totalEntries = userIdTotalChallenges(userID);
        ChallengesStack mainStack = createUserIdChallengesStack(totalEntries,userID);
        boolean exit = false;
        int userInput;
        int indexTracker = 1;           // Can put (int)(Math.random()* (mainStack.cap))+1 to start from random Challenge.
        do {
            // Persistent Browsing State: Save current stack state into DB, so when the user logs back in, they resume where they left off.
            Challenges temp = mainStack.peep(indexTracker);
            if(temp == null){
                System.out.println("No challenge found at this index.");
                break; // or return
            }
            System.out.println(temp.challengeDesc);
            System.out.println(temp.accepted);
            System.out.println(temp.accomplished);
            System.out.println("1. PREV");
            System.out.println("2. ACCEPT CHALLENGE");
            System.out.println("3. NEXT");
            System.out.println("4. RANDOM");
            System.out.println("5. BACK");
            userInput = sc.nextInt();
            switch (userInput) {
                case 1:
                    if (indexTracker <= 1) {
                        indexTracker = mainStack.cap - 1;     // Circular Queue Behaviour.
                    } else {
                        indexTracker--;
                    }
                    break;

                case 2:
                    if (temp.accepted.equals("No")) {
                        String q1 = "UPDATE challenges_table SET accepted='Yes' WHERE challenge_ID=?";
                        PreparedStatement pst1 = con.prepareStatement(q1);
                        pst1.setInt(1,temp.cID);
                        int r = pst1.executeUpdate();
                        if(r>0) {
                            temp.accepted = "Yes";
                            System.out.println("CHALLENGE ACCEPTED.");
                        }else{
                            System.out.println("COULD NOT ACCEPT THE CHALLENGE. PLEASE TRY AGAIN.");
                        }
                    } else if(temp.accepted.equals("Yes")){
                        System.out.println("CHALLENGE ALREADY IN PROGRESS.");
                    } else if(temp.accepted.equals("Done")){
                        System.out.println("ALREADY ACCOMPLISHED");
                    }
                    break;

                case 3:
                    if (indexTracker >= mainStack.cap - 1) {
                        indexTracker = 1;
                    } else {
                        indexTracker++;
                    }
                    break;

                case 4:
                    indexTracker = (int) (Math.random() * (mainStack.cap)) + 1;
                    break;

                case 5:
                    exit = true;
            }

        } while (!exit);
    }
}
class UserNameChallenges {
    Scanner sc = new Scanner(System.in);
    Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int userNameTotalChallenges(String userName) throws SQLException{
        int returnValue = 0;
        String q1 = "{call getChallengesCountByUserName(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setString(1,userName);
        ResultSet rs1 = cst1.executeQuery();
        while (rs1.next()) {
            returnValue = rs1.getInt(1);
        }
        return returnValue;
    }

    public ChallengesStack createUserNameChallengesStack(int size) throws SQLException{
        ChallengesStack returnVariable = new ChallengesStack(size);
        String q1 = "{call getAllChallengesByUserName()}";
        CallableStatement cst1 = con.prepareCall(q1);
        ResultSet rs1 = cst1.executeQuery();
        while (rs1.next()) {
            returnVariable.push(new Challenges(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
        }
        return returnVariable;
    }


    public void browseChallengesByUserID(String userName) throws SQLException {
        int totalEntries = userNameTotalChallenges(userName);
        ChallengesStack mainStack = createUserNameChallengesStack(totalEntries);
        boolean exit = false;
        int userInput;
        int indexTracker = 1;           // Can put (int)(Math.random()* (mainStack.cap))+1 to start from random Challenge.
        do {
            // Persistent Browsing State: Save current stack state into DB, so when the user logs back in, they resume where they left off.
            Challenges temp = mainStack.peep(indexTracker);
            System.out.println(temp.challengeDesc);
            System.out.println(temp.accepted);
            System.out.println(temp.accomplished);
            System.out.println("1. PREV");
            System.out.println("2. ACCEPT CHALLENGE");
            System.out.println("3. NEXT");
            System.out.println("4. RANDOM");
            System.out.println("5. BACK");
            userInput = sc.nextInt();
            switch (userInput) {
                case 1:
                    if (indexTracker <= 1) {
                        indexTracker = mainStack.cap - 1;     // Circular Queue Behaviour.
                    } else {
                        indexTracker--;
                    }
                    break;

                case 2:
                    if (temp.accepted.equals("No")) {
                        String q1 = "UPDATE challenges_table SET accepted='Yes' WHERE challenge_ID=?";
                        PreparedStatement pst1 = con.prepareStatement(q1);
                        pst1.setInt(1,temp.cID);
                        int r = pst1.executeUpdate();
                        if(r>0) {
                            temp.accepted = "Yes";
                            System.out.println("CHALLENGE ACCEPTED.");
                        }else{
                            System.out.println("COULD NOT ACCEPT THE CHALLENGE. PLEASE TRY AGAIN.");
                        }
                    } else if(temp.accepted.equals("Yes")) {
                        System.out.println("CHALLENGE ALREADY IN PROGRESS.");
                    } else if(temp.accepted.equals("Done")) {
                        System.out.println("ALREADY ACCOMPLISHED");
                    }
                    break;

                case 3:
                    if (indexTracker >= mainStack.cap - 1) {
                        indexTracker = 1;
                    } else {
                        indexTracker++;
                    }
                    break;

                case 4:
                    indexTracker = (int) (Math.random() * (mainStack.cap)) + 1;
                    break;

                case 5:
                    exit = true;
            }

        } while (!exit);
    }
}
class CurrentUserDetails {

    Scanner sc = new Scanner(System.in);
    Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int user_id;
    public String username, password;

    public CurrentUserDetails(int user_id,String username, String password){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

}
class AddChallenge {
    Scanner sc = new Scanner(System.in);
    Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addChallenge(LoginSignup currentUser) throws SQLException {
        System.out.println("Enter the challenge description:");
        String challengeDescription = sc.nextLine();

        String q1 = "{call addNewChallenge(?,?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1,currentUser.userId);
        cst1.setString(2,challengeDescription);
        int r = cst1.executeUpdate();

        if(r > 0){
            System.out.println("CHALLENGE ADDED SUCCESSFULLY.");
        }else{
            System.out.println("PLEASE TRY AGAIN.");
        }
    }
}
class MyChallenges {
    Scanner sc = new Scanner(System.in);
    Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void myChallengesSection(LoginSignup currentUser) throws SQLException {
        boolean exit = false;
        ArrayList<Challenges> myChallenges = new ArrayList<>();

        String q1 = "{call getAllChallengesByUserID(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1, currentUser.userId);
        ResultSet rs1 = cst1.executeQuery();

        while (rs1.next()) {
            myChallenges.add(new Challenges(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
        }

        for (Challenges x : myChallenges) {
            System.out.println(x);
        }

        System.out.println();
        while (!exit) {
            System.out.println();
            System.out.println("WHAT COURSE OF ACTION DO YOU PREFER?");
            System.out.println();
            System.out.println("1. DELETE A CHALLENGE");
            System.out.println("2. MARK A CHALLENGE AS COMPLETED");
            System.out.println("3. BROWSE THROUGH MY CHALLENGES");
            System.out.println("4. EXIT");
            int choice1 = sc.nextInt();

            switch(choice1){
                case 1:
                    System.out.println("ENTER THE CHALLENGE ID YOU WANT TO DELETE:");
                    int delChallengeID = sc.nextInt();
                    deleteMyChallenge(delChallengeID);
                    break;

                case 2:
                    System.out.println("ENTER THE CHALLENGE ID YOU WANT TO MARK AS COMPLETED.");
                    int accChallengeID = sc.nextInt();
                    markChallengeAsCompleted(accChallengeID);
                    break;

                case 3:
                    // Loading Animation...
                    UserIdChallenges callerTemp = new UserIdChallenges();
                    callerTemp.browseChallengesByUserID(currentUser.userId);
                    break;

                case 4:
                    exit = true;
                    break;

                default:
            }
        }
    }

    public void deleteMyChallenge(int delID) throws SQLException {
        String q1 = "{call delChallenge(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1,delID);
        int r = cst1.executeUpdate();
        if(r > 0){
            System.out.println("DELETION SUCCESSFUL.");
        }else{
            System.out.println("PLEASE TRY AGAIN.");
        }
    }

    public void markChallengeAsCompleted(int accomplishedID) throws SQLException {

        String q1 = "{call markAsAccomplished(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1,accomplishedID);
        int r = cst1.executeUpdate();
        if(r > 0){
            System.out.println("CONGRATULATIONS!!!");
            System.out.println("CHALLENGE ACCOMPLISHED.");
        }else{
            System.out.println("PLEASE TRY AGAIN.");
        }

    }

}
class MyAccomplishments {
    Scanner sc = new Scanner(System.in);
    Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflectofinal", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Challenges> makeAccArrayList(int userID) throws SQLException {
        ArrayList<Challenges> temp = new ArrayList<>();
        String q1 = "{call getAccomplishmentsByUserID(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1, userID);
        ResultSet rs1 = cst1.executeQuery();
        while (rs1.next()) {
            temp.add(new Challenges(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
        }
        return temp;
    }

    public void browseAccomplishments(LoginSignup currentUser) throws SQLException {
        boolean exit = false;
        ArrayList<Challenges> myAccomplishments = makeAccArrayList(currentUser.userId);
        for (Challenges x : myAccomplishments) {
            System.out.println(x);
        }
        System.out.println();
        while (!exit) {
            System.out.println("WHAT COURSE OF ACTION DO YOU PREFER?");
            System.out.println("1. MARK AN ACCOMPLISHMENT AS UNACCOMPLISHED");
            System.out.println("2. BROWSE THROUGH MY ACCOMPLISHMENTS");
            System.out.println("3. EXIT");
            int choice1 = sc.nextInt();

            switch(choice1){
                case 1:
                    System.out.println("ENTER THE CHALLENGE ID YOU WANT TO MARK AS UNACCOMPLISHED:");
                    int unAccomplishID = sc.nextInt();
                    if(unAccomplishChallenge(unAccomplishID)){
                        myAccomplishments = makeAccArrayList(currentUser.userId);
                        for (Challenges x : myAccomplishments) {
                            System.out.println(x);
                        }
                    }
                    break;

                case 2:
                    // Loading Animation...
                    if(browseAccomplishmentsByUserID(currentUser.userId)){
                        myAccomplishments = makeAccArrayList(currentUser.userId);
                        for (Challenges x : myAccomplishments) {
                            System.out.println(x);
                        }
                    }
                    break;

                case 3:
                    exit = true;
                    break;

                default:
            }
        }
    }

    public boolean unAccomplishChallenge(int unAccomplishID) throws SQLException {
        String q1 = "{call markAsUnAccomplished(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1,unAccomplishID);
        int r = cst1.executeUpdate();

        if(r > 0){
            System.out.println("UN-ACCOMPLISHED");
            return true;
        }else{
            System.out.println("PLEASE TRY AGAIN");
            return false;
        }
    }

    public int userIdTotalAccomplishments(int userID) throws SQLException{
        int returnValue = 0;
        String q1 = "{call getAccomplishmentsCountByUserID(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1,userID);
        ResultSet rs1 = cst1.executeQuery();
        while (rs1.next()) {
            returnValue = rs1.getInt(1);
        }
        return returnValue;
    }

    public ChallengesStack createUserIdAccomplishmentsStack(int size, int userID) throws SQLException{
        ChallengesStack returnVariable = new ChallengesStack(size);
        String q1 = "{call getAccomplishmentsByUserID(?)}";
        CallableStatement cst1 = con.prepareCall(q1);
        cst1.setInt(1,userID);
        ResultSet rs1 = cst1.executeQuery();
        while (rs1.next()) {
            returnVariable.push(new Challenges(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
        }
        return returnVariable;
    }

    public boolean browseAccomplishmentsByUserID(int userID) throws SQLException {
        boolean didChangeArrayList = false;
        int totalEntries = userIdTotalAccomplishments(userID);
        ChallengesStack mainStack = createUserIdAccomplishmentsStack(totalEntries,userID);
        boolean exit = false;
        int userInput;
        // Challenges temp;
        int indexTracker = 1;           // Can put (int)(Math.random()* (mainStack.cap))+1 to start from random Challenge.
        do {
            // Persistent Browsing State: Save current stack state into DB, so when the user logs back in, they resume where they left off.
            Challenges temp = mainStack.peep(indexTracker);
            if(temp == null){
                System.out.println("EMPTY.");
                break;
            }
            System.out.println(temp.challengeDesc);
            System.out.println(temp.accomplished);
            System.out.println("1. PREV");
            System.out.println("2. UN-ACCOMPLISH");
            System.out.println("3. NEXT");
            System.out.println("4. RANDOM");
            System.out.println("5. BACK");
            userInput = sc.nextInt();
            switch (userInput) {
                case 1:
                    if (indexTracker <= 1) {
                        indexTracker = mainStack.cap - 1;     // Circular Queue Behaviour.
                    } else {
                        indexTracker--;
                    }
                    break;

                case 2:
                    if (temp.accomplished.equals("Yes")) {
                        String q1 = "UPDATE challenges_table SET accomplished='No' WHERE challenge_ID=?";
                        PreparedStatement pst1 = con.prepareStatement(q1);
                        pst1.setInt(1, temp.cID);
                        int r = pst1.executeUpdate();
                        if (r > 0) {
                            temp.accomplished = "No";
                            System.out.println("UN-ACCOMPLISHED");
                            didChangeArrayList = true;
                        } else {
                            System.out.println("PLEASE TRY AGAIN.");
                        }
                    }
                    break;

                case 3:
                    if (indexTracker >= mainStack.cap - 1) {
                        indexTracker = 1;
                    } else {
                        indexTracker++;
                    }
                    break;

                case 4:
                    indexTracker = (int) (Math.random() * (mainStack.cap)) + 1;
                    break;

                case 5:
                    exit = true;
            }

        } while (!exit);

        return didChangeArrayList;
    }
}