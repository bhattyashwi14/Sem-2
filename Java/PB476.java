import java.util.*;
import java.sql.*;
public class PB476
{
    public static void main(String[] args) throws Exception
    {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
        if(con!=null)
        {
            System.out.println("Connection done");
        }
        Scanner sc=new Scanner(System.in);
        String confirmed="Create table if not exists confirmed (id int, name varchar(60), rank int)";
        String rejected="Create table if not exists rejected (id int, name varchar(60), rank int)";
        Statement st=con.createStatement();
        st.execute(confirmed); st.execute(rejected);
        String data="Select * from ljuadmission order by rank";
        String addData="Insert into confirmed values(?,?,?)";
        String addData1="Insert into rejected values(?,?,?)";
        PreparedStatement pst=con.prepareStatement(addData);
        PreparedStatement pst1=con.prepareStatement(addData1);
        ResultSet rs=st.executeQuery(data);
        int count=1;
        while(rs.next())
        {
                if(count<=5)
                {
                    pst.setInt(1, rs.getInt(1));
                    pst.setString(2, rs.getString(2));
                    pst.setInt(3, rs.getInt(3));
                    //pst.executeUpdate();
                }
                else
                {
                    pst1.setInt(1, rs.getInt(1));
                    pst1.setString(2, rs.getString(2));
                    pst1.setInt(3, rs.getInt(3));
                   //pst1.executeUpdate();
                }
                count++;
        }

        int x;
        do
        {
            System.out.println("Press 1 to display names of admission confirmed student");
            System.out.println("Press 2 to display name of admission rejected students");
            System.out.println("Press 3 to remove any student from confirmed table through id");
            System.out.println("Press 4 to print additional data of database");
            System.out.println("Press 5 to exit");
            System.out.print("Enter your choice:");
            x=sc.nextInt();
            System.out.println();
            switch (x)
            {
                case 1:
                    String confirmed_st="Select name from confirmed";
                    ResultSet rs1=st.executeQuery(confirmed_st);
                    System.out.println("Name of students whose admission is confirmed:");
                    while(rs1.next())
                    {
                        System.out.println(rs1.getString("name"));
                    }
                    System.out.println();
                    break;
                case 2:
                    String rejected_st="Select name from rejected";
                    ResultSet rs2=st.executeQuery(rejected_st);
                    System.out.println("Name of students whose admission is rejected:");
                    while(rs2.next())
                    {
                        System.out.println(rs2.getString(1));
                    }
                    System.out.println();
                    break;
                case 3:
                    con.setAutoCommit(false);
                    String remove_st="Delete from confirmed where id=?";
                    PreparedStatement pst3=con.prepareStatement(remove_st);
                    System.out.print("Enter the ID of student whose admission you want to withdraw:");
                    int Id=sc.nextInt();
                    System.out.println();
                    System.out.print("Are you sure you want to withdraw your admission?(Yes/No):");
                    String ans=sc.next();
                    System.out.println();
                    if(ans.equalsIgnoreCase("yes"))
                    {
                        pst3.setInt(1,Id);
                        int n=pst3.executeUpdate();
                        if(n>0)
                        {
                            System.out.println(Id+" has withdrawn their admission");
                        }
                        else
                        {
                            System.out.println("Enter a valid Id:");
                        }
                        con.commit();
                    }
                    System.out.println();
                    break;
                case 4:
                    DatabaseMetaData dm=con.getMetaData();
                    System.out.println("User Name:"+dm.getUserName());
                    System.out.println("Driver Name:"+dm.getDriverName());
                    System.out.println("Driver Version:"+dm.getDriverVersion());
                    System.out.println("URL:"+dm.getURL());
                    System.out.println("Database Product Name:"+dm.getDatabaseProductName());
                    System.out.println("Database Product Version Name:"+dm.getDatabaseProductVersion());
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Exiting!!!");
                    break;
                default:
                    System.out.println("Please enter a valid choice");
            }
        }while(x!=5);
    }
}
