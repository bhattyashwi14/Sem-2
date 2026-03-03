import java.util.*;
class Student245
{
    int id;
    String name;
    double GPA;
    public Student245(int id, String name, double GPA)
    {
        this.id = id;
        this.name = name;
        this.GPA = GPA;
    }

    public double getGPA()
    {
        return GPA;
    }
    public String toString() {
        return "Name:"+name+", ID:"+id+", GPA:"+GPA+"\n";
    }
}
public class PB245
{
    public static void main(String[] args)
    {
        Stack<Student245> st=new Stack<>();
        Student245 ob1=new Student245(1,"yashi",9.25);
        Student245 ob2=new Student245(2,"j",8.97);
        Student245 ob3=new Student245(3,"vish",8.58);
        st.push(ob2); st.push(ob3); st.push(ob1);
        st.sort(Comparator.comparing(Student245::getGPA).reversed());
        System.out.println(st);
    }
}
