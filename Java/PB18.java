abstract class Vegetable
{
    String colour,name;
    public String toString()
    {
        return "";
    }
}
class Potato extends Vegetable
{
    public String toString()
    {
        name="Potato";
        colour="Brown";
        return "Name:"+name+" \n Colour:"+colour;
    }
}
class Brinjal extends Vegetable
{
    public String toString()
    {
        name="Brinjal";
        colour="Purple";
        return "Name:"+name+" \n Colour:"+colour;
    }
}
class Tomato extends Vegetable
{
    public String toString()
    {
        name="Tomato";
        colour="Red";
        return "Name:"+name+" \n Colour:"+colour;
    }
}
public class PB18
{
    public static void main(String[] args)
    {
        Potato p=new Potato();
        Brinjal b=new Brinjal();
        Tomato t=new Tomato();
        System.out.println(p);
        System.out.println(b);
        System.out.println(t);
    }
}
