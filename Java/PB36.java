interface l1
{

}
interface l2
{

}
interface l3 extends l1,l2
{

}
interface l4
{

}
class X implements l3
{

}
class W extends X implements l4
{

}
public class PB36
{
    public static void main(String[] args) {
        W ob = new W();
        System.out.println(ob instanceof X);
        System.out.println(ob instanceof l4);
        System.out.println(ob instanceof l1);
        System.out.println(ob instanceof l2);
        System.out.println(ob instanceof l3);
    }
}
