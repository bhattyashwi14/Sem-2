import java.util.*;
import java.sql.*;
public class TrainTicketManagement
{
    public static void main(String[] args) throws Exception
    {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
        if(con!=null)
        {
            System.out.println("Connection done");
        }
        Scanner sc=new Scanner(System.in);
        Statement st=con.createStatement();
        int x;
        do {
            System.out.println("Enter 1 to Add Train");
            System.out.println("Enter 2 to Retrieve Train");
            System.out.println("Enter 3 to Update Train");
            System.out.println("Enter 4 to Delete Train");
            System.out.println("Enter 5 to Book Ticket");
            System.out.println("Enter 6 to Cancel Booking");
            System.out.println("Enter 7 to Update Booking");
            System.out.println("Enter 8 to View Booking Detail");
            System.out.println("Enter 9 to Database Metadata");
            System.out.println("Enter 10 to ResultSet Metadata");
            System.out.println("Enter 11 to Exit");
            System.out.print("Enter your choice:");
            x=sc.nextInt();
            System.out.println();

            switch (x)
            {
                case 1:
                    String addTrain="Insert into Train values(?,?,?,?)";
                    PreparedStatement pst1=con.prepareStatement(addTrain);
                    System.out.print("Enter train number:");
                    int tn=sc.nextInt();
                    System.out.print("Enter source:");
                    String s=sc.next();
                    System.out.print("Enter destination:");
                    String dest=sc.next();
                    System.out.print("Enter available seats:");
                    int a=sc.nextInt();
                    pst1.setInt(1,tn); pst1.setString(2,s);
                    pst1.setString(3,dest); pst1.setInt(4,a);
                    int n1=pst1.executeUpdate();
                    if(n1>0)
                    {
                        System.out.println("Train added");
                    }
                    else
                    {
                        System.out.println("Unknown error occurred");
                    }
                    System.out.println();
                    break;
                case 2:
                    String getTrain="Select * from train where train_num=?";
                    System.out.print("Enter train number:");
                    int tNo=sc.nextInt();
                    System.out.println();
                    PreparedStatement pst2=con.prepareStatement(getTrain);
                    pst2.setInt(1,tNo);
                    ResultSet rs=pst2.executeQuery();
                    while(rs.next())
                    {
                        System.out.println("Source:"+rs.getString("source"));
                        System.out.println("Destination:"+rs.getString("destination"));
                        System.out.println("Available seats:"+rs.getInt("available_seats"));
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter train number whose details you want to update:");
                    int taNo=sc.nextInt();
                    System.out.println();
                    String update="Update train set source=?,destination=?,available_seats=? where train_num=?";
                    System.out.print("Enter updated source:");
                    String us=sc.next();
                    System.out.print("Enter updated destination:");
                    String ud=sc.next();
                    System.out.print("Enter updated no. of seats:");
                    int uns=sc.nextInt();
                    System.out.println();
                    PreparedStatement pst3=con.prepareStatement(update);
                    pst3.setString(1,us); pst3.setString(2,ud);
                    pst3.setInt(3,uns); pst3.setInt(4,taNo);
                    int n3=pst3.executeUpdate();
                    if(n3>0)
                    {
                        System.out.println("Train details updated!");
                    }
                    else
                    {
                        System.out.println("Unknown error occurred");
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter train number of the train you want to  delete:");
                    int tnum=sc.nextInt();
                    String delete="Delete from train where train_num=?";
                    PreparedStatement pst4=con.prepareStatement(delete);
                    pst4.setInt(1,tnum);
                    int n4=pst4.executeUpdate();
                    System.out.println();
                    if(n4>0)
                    {
                        System.out.println("Train deleted!");
                    }
                    else
                    {
                        System.out.println("Unknown error occurred");
                    }
                    System.out.println();
                    break;
                case 5:
                    con.setAutoCommit(false);
                    System.out.print("Enter booking id:");
                    int bid=sc.nextInt();
                    System.out.print("Enter train number:");
                    int tnid=sc.nextInt();
                    System.out.print("Enter source:");
                    String sor=sc.next();
                    System.out.print("Enter destination:");
                    String des=sc.next();
                    System.out.print("Enter no. of seats:");
                    int sea=sc.nextInt();
                    System.out.println();
                    String booking="Insert into tickets values (?,?,?,?,?)";
                    PreparedStatement pst5=con.prepareStatement(booking);
                    pst5.setInt(1,bid); pst5.setInt(2,tnid); pst5.setString(4,sor);
                    pst5.setString(5,des); pst5.setInt(3,sea);
                    String q="Select available_seats from train where train_num=?";
                    PreparedStatement pst51=con.prepareStatement(q);
                    pst51.setInt(1,tnid);
                    ResultSet rs51=pst51.executeQuery();
                    while (rs51.next())
                    {
                        if(rs51.getInt(1)>sea)
                        {
                            int n5=pst5.executeUpdate();
                            String update_seats="Update train set available_seats=available_seats-"+sea+" " +
                                    "where train_num="+tnid;
                            st.execute(update_seats);
                            if(n5>0)
                            {
                                System.out.println("Booking done!");
                            }
                            else
                            {
                                System.out.println("Unknown error occurred");
                            }
                            con.commit();
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("Not enough seats available!");
                            System.out.println();
                        }
                    }
                    break;
                case 6:
                    con.setAutoCommit(false);
                    System.out.print("Enter your booking id:");
                    int bId=sc.nextInt();
                    String cancel="Delete from tickets where booking_id=?";
                    PreparedStatement pst6=con.prepareStatement(cancel);
                    System.out.println("Are you sure?(Yes/No):");
                    String ans=sc.next();
                    if(ans.equalsIgnoreCase("yes"))
                    {
                        pst6.setInt(1,bId);
                        String seat="Select seat,train_num from tickets where booking_id="+bId;
                        ResultSet rs0=st.executeQuery(seat);
                        int seats=0; int tid=0;
                        while(rs0.next())
                        {
                            seats=rs0.getInt(1); tid=rs0.getInt(2);
                        }
                        String addSeats="Update train set available_seats=available_seats+"+seats+
                                " where train_num="+tid;
                        st.execute(addSeats);
                        int n6=pst6.executeUpdate();
                        if(n6>0)
                        {
                            System.out.println("Booking cancelled");
                        }
                        else
                        {
                            System.out.println("Unknown error occurred");
                        }
                        con.commit();
                        System.out.println();
                    }
                    break;
                case 7:
                    con.setAutoCommit(false);
                    System.out.print("Enter your booking Id:");
                    int bID=sc.nextInt();
                    System.out.print("Enter your revised seat count:");
                    int stc=sc.nextInt();
                    String updateBooking="Update tickets set seat=? where booking_id=?";
                    PreparedStatement pst7=con.prepareStatement(updateBooking);
                    String q6="Select seat,train_num from tickets where booking_id="+bID;
                    ResultSet rss6=st.executeQuery(q6); rss6.next();
                    int extra=stc-rss6.getInt(1);  int tnID=rss6.getInt(2);
                    String q7="Select available_seats from train where train_num="+tnID;
                    ResultSet rss7=st.executeQuery(q7); rss7.next();
                    if(rss7.getInt(1)>extra)
                    {
                        pst7.setInt(1,stc); pst7.setInt(2,bID);
                        int n7=pst7.executeUpdate();
                        String q5="Update train set available_seats=available_seats-"+extra+" where train_num="+tnID;
                        st.execute(q5);
                        if(n7>0)
                        {
                            System.out.println("Seats updated");
                        }
                        else
                        {
                            System.out.println("Unknown error occurred");
                        }
                        con.commit();
                        System.out.println();
                    }
                    else
                    {
                        System.out.println("Not enough seats available");
                        System.out.println();
                    }
                    break;
                case 8:
                    System.out.print("Enter your booking id:");
                    int bd=sc.nextInt();
                    String retrive="Select * from tickets where booking_id=?";
                    PreparedStatement pst8= con.prepareCall(retrive);
                    pst8.setInt(1,bd);
                    ResultSet rs8=pst8.executeQuery();
                    while(rs8.next())
                    {
                        System.out.println("Booking ID:"+rs8.getInt(1));
                        System.out.println("Train number:"+rs8.getInt(2));
                        System.out.println("Seats:"+rs8.getInt(3));
                        System.out.println("Source:"+rs8.getString(4));
                        System.out.println("Destination:"+rs8.getString(5));
                    }
                    System.out.println();
                    break;
                case 9:
                    DatabaseMetaData dm=con.getMetaData();
                    System.out.println("Product Name:"+dm.getDatabaseProductName());
                    System.out.println("Product Version:"+dm.getDatabaseProductVersion());
                    System.out.println("Driver Name:"+dm.getDriverName());
                    System.out.println("Driver version:"+dm.getDriverVersion());
                    System.out.println("URL:"+dm.getURL());
                    System.out.println("User Name:"+dm.getUserName());
                    break;
                case 10:
                    String q10="Select * from train";
                    ResultSet rs10=st.executeQuery(q10);
                    ResultSetMetaData rmd=rs10.getMetaData();
                    System.out.println("Table name:"+rmd.getTableName(1));
                    System.out.println("Column count:"+rmd.getColumnCount());
                    for(int i=1;i<= rmd.getColumnCount();i++)
                    {
                        System.out.println("Column Number:"+i);
                        System.out.println("Column Name"+rmd.getColumnName(i));
                        System.out.println("Column Type:"+rmd.getColumnTypeName(i));
                    }
                    System.out.println();
                    break;
                case 11:
                    System.out.println("Exiting!!!");
                    break;
            }
        }while(x!=11);
    }
}
