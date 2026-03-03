abstract class Pen
{
    abstract void write();
    abstract void refill();
}
class FountainPen extends Pen
{
    void write()
    {
        System.out.println("write");
    }
    void refill()
    {
        System.out.println("refill");
    }
    void nib()
    {
        System.out.println("nib");
    }
}
class Monkey
{
    void jump()
    {
        System.out.println("m-jump");
    }
    void bite()
    {
        System.out.println("m-bite");
    }
}
interface BasicAnimal
{
    void sleep();
    void eat();
}
class Human extends Monkey implements BasicAnimal
{
    public void sleep()
    {
        System.out.println("sleep");
    }
    public void eat()
    {
        System.out.println("eat");
    }
}
public class PB47
{

}
