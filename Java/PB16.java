abstract class Person
{
   String name;
   String address;
   String phone_no;
   String email;
   Person(String n,String a,String p,String e)
   {
       name=n;
       address=a;
       phone_no=p;
       email=e;
   }
   void display()
   {
       System.out.println("Name:"+name);
       System.out.println("Address:"+address);
       System.out.println("Phone np.:"+phone_no);
       System.out.println("Email:"+email);
   }
}
class Student extends Person
{
    String enrollment;
    Student(String n,String a,String p,String e,String en)
    {
       super(n,a,p,e);
       enrollment=en;
       super.display();
       this.display();
    }
    void display()
    {
        System.out.println("Enrollment:"+enrollment);
    }
}
class Employee extends Person
{
    String office,designation;
    double salary;
    Employee(String n,String a,String p,String e,String o,String d,double s)
    {
        super(n,a,p,e);
        office=o;
        designation=d;
        salary=s;
        super.display();
        this.display();
    }
    void display()
    {
        System.out.println("Office:"+office);
        System.out.println("Designation:"+designation);
        System.out.println("Salary:"+salary);
    }
}
public class PB16
{
    public static void main(String[] args)
    {
       Student ob=new Student("xyz","abc","12345","abc@xyz","0987");
       Person ob1=new Student("xyz","abc","12345","abc@xyz","0987");
       Employee ob2=new Employee("xyz","abc","12345","abc@xyz","off","engineeer",80000);
       Person ob3=new Employee("xyz","abc","12345","abc@xyz","off","engineeer",80000);
    }
}
