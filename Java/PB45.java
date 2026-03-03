interface polygon
{
  default void getsides(int x,int y)
  {
      System.out.println("getSides method");
  }
  void getArea();
}
class Rectangle1 implements polygon
{
    int len,bre;
    public void getsides(int l, int b)
    {
        len=l;
        bre=b;
    }
    public void getArea()
    {
        int a=len*bre;
        System.out.println("Area of Rectangle:"+a);
    }
}
class Square implements polygon
{
    public void getArea()
    {
        System.out.println("getArea method of square");
    }
}
public class PB45
{
    public static void main(String[] args)
    {
        Rectangle1 ob=new Rectangle1();
        Square ob1 =new Square();
        ob.getsides(2,5); ob.getArea();
        ob1.getsides(2,2);ob1.getArea();
    }
}
