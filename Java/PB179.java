class BankAccount
{
    int Amount;
    BankAccount(int Amount)
    {
        this.Amount=Amount;
    }
    synchronized void withdrawal(int withdraw)
    {
        if(withdraw<=Amount)
        {
            System.out.println("Rupees "+withdraw+" withdrew by "+Thread.currentThread().getName());
            Amount=Amount-withdraw;
        }
        else
        {
            System.out.println("Insufficient balance");
        }
    }
}
class Customer extends Thread
{
    BankAccount ob;
    int w;
    public Customer(BankAccount ob, int w)
    {
        this.ob = ob;
        this.w = w;
    }
    public void run()
    {
        ob.withdrawal(w);
    }
}
public class PB179
{
    public static void main(String[] args)
    {
        BankAccount ob=new BankAccount(7000);
       Customer c1=new Customer(ob,5000);
       Customer c2=new Customer(ob,5000);
       c1.start();
       c2.start();
    }
}
