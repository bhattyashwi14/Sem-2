abstract class Shape
{
    abstract void area(double x,double y);
}
class Triangle extends Shape
{
    void area(double h,double b)
    {
        double a=0.5*h*b;
        System.out.println("Area of Triangle="+a);
    }
}
class Rectangle extends Shape
{
    void area(double l,double b)
    {
        double a=l*b;
        System.out.println("Area of rectangle="+a);
    }
}
class Circle extends Shape
{
    void area(double r,double r1)
    {
        double a=3.14*r*r1;
        System.out.println("Area of Circle="+a);
    }
}
public class PB17
{
    public static void main(String[] args)
    {
        Triangle ob1=new Triangle();
        ob1.area(2,2);
        Rectangle ob2=new Rectangle();
        ob2.area(5,2);
        Circle ob3=new Circle();
        ob3.area(10,10);
    }
}
