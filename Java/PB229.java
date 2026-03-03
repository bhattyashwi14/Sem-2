import java.util.*;
class Student1
{
   double marks;
   String name;
    public Student1(double marks, String name)
    {
        this.marks = marks;
        this.name = name;
    }
    public double getMarks()
    {
        return marks;
    }
    public String toString()
    {
        return "Name:"+name+", Marks:"+marks+"\n";
    }
}
public class PB229
{
    public static void main(String[] args)
    {
        ArrayList<Student1> st=new ArrayList<>();
        Student1 ob1=new Student1(18.5,"Devarsh");
        Student1 ob2=new Student1(20.5,"Viraj");
        Student1 ob3=new Student1(17.5,"Niddhi");
        st.add(ob1); st.add(ob2); st.add(ob3);
        st.sort(Comparator.comparing(Student1::getMarks).reversed());
        Iterator i=st.iterator();
        while(i.hasNext())
        {
            System.out.println(i.next());
        }
    }
}
