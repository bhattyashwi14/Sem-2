interface University
{
    void takeExam();
}
interface State
{
    void getName();
}
interface StateUniversity extends University,State
{
    void print();
}
class TestState implements StateUniversity
{
    public void takeExam()
    {
        System.out.println("takeExam method");
    }
    public void getName()
    {
        System.out.println("getName method");
    }
    public void print()
    {
        System.out.println("print method");
    }
}
public class P39
{
    public static void main(String[] args)
    {
        TestState ob=new TestState();
        ob.takeExam();
        ob.getName();
        ob.print();
    }
}
