import java.util.*;
class Medicine
{
    String name;
    double price;
    int quantity;
    public Medicine(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName1()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public String toString()
    {
        return "Name="+name+
                ", Price="+price +
                ", Quantity="+quantity+"\n";
    }
}
public class Pharmacy
{
    Scanner sc=new Scanner(System.in);
    static LinkedList <Medicine> med;
    public Pharmacy()
    {
        this.med = new LinkedList<>();;
    }
    synchronized void addMedicine(Medicine ob)
    {
        med.add(ob);
    }
    synchronized void removeMedicine(String medName)
    {
        int index=-1;
        for(int i=0;i<med.size();i++)
        {
            if(med.get(i).getName1().equalsIgnoreCase(medName))
            {
                med.remove(i);
                index=i;
            }
        }
        if(index==-1)
        {
            System.out.println("No such medicine found");
        }
    }
    synchronized void displayMedicine()
    {
        System.out.println(med);
    }
    synchronized void displayMedicine(String medName)
    {
        int index=-1;
        for(int i=0;i<med.size();i++)
        {
            if(med.get(i).getName1().equalsIgnoreCase(medName))
            {
                index=i;
            }
        }
        if(index==-1)
        {
            System.out.println("No such medicine found");
        }
        else
        {
            System.out.println(med.get(index));
        }
    }
    synchronized void sellMedicine(String medName, int quantity)
    {
        int index=-1;
        for(int i=0;i<med.size();i++)
        {
            if(med.get(i).getName1().equalsIgnoreCase(medName))
            {
                index=i;
            }
        }
        if(index==-1)
        {
            System.out.println("No such medicine found");
        }
        else
        {
            if(med.get(index).getQuantity()>=quantity)
            {
                System.out.println(med.get(index).getName1()+" with quantity "+med.get(index).getQuantity()+
                        " sold ");
                med.get(index).quantity=med.get(index).quantity-quantity;
                System.out.println("Available quantity:"+med.get(index).quantity);
            }
            else
            {
                System.out.println("Not enough quantity available!");
                System.out.println("Available quantity:"+med.get(index).quantity);
            }
        }
    }
    synchronized void updateMedicine(String medName)
    {
        int index=-1;
        for(int i=0;i<med.size();i++)
        {
            if(med.get(i).getName1().equalsIgnoreCase(medName))
            {
                index=i;
            }
        }
        if(index==-1)
        {
            System.out.println("No such medicine found");
        }
        else
        {
            System.out.print("Enter the updated Price:");
            double p=sc.nextDouble();
            System.out.print("Enter the updated Quantity:");
            int q=sc.nextInt();
            System.out.print("Enter the updated Name:");
            String n=sc.next();
            med.get(index).quantity=q;
            med.get(index).price=p;
            med.get(index).name=n;
        }
    }
}
class PMSystem extends Thread
{
    static Pharmacy pharmacy=null;
    Scanner sc=new Scanner(System.in);
    public static void main(String[] args)
    {
        ArrayList<Thread> customerThread=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        pharmacy=new Pharmacy();
        pharmacy.addMedicine(new Medicine("Paracetamol",50.0,20));
        pharmacy.addMedicine(new Medicine("Ibuprofen",80.0,15));
        pharmacy.addMedicine(new Medicine("Saridone",120.0,100));
        pharmacy.addMedicine(new Medicine("Dolo",150.0,30));
        pharmacy.addMedicine(new Medicine("Disprine",200.0,10));
        int x;
        do
        {
            System.out.println("Enter 1 to display medicines");
            System.out.println("Enter 2 to sell medicines");
            System.out.println("Enter 3 to add medicines");
            System.out.println("Enter 4 to remove medicines");
            System.out.println("Enter 5 to update medicines");
            System.out.println("Enter 6 to exit");
            System.out.print("Enter your choice:");
            x=sc.nextInt();
            switch(x)
            {
                case 1:
                    int x1;
                    do {
                        System.out.println("Enter 1 to display medicine list");
                        System.out.println("Enter 2 to display details of specific medicine");
                        System.out.println("Enter 3 to exit");
                        System.out.print("Enter your choice:");
                        x1=sc.nextInt();
                    switch (x1)
                    {
                        case 1:
                            pharmacy.displayMedicine();
                            break;
                        case 2:
                            System.out.print("Enter the name of medicine you're looking for:");
                            String medName=sc.next();
                            pharmacy.displayMedicine(medName);
                            break;
                        case 3:
                            System.out.println("Exiting");
                            break;
                        default:
                            System.out.println("Enter a valid choice");
                    }
                    }while(x1!=3);
                    break;
                case 2:
                    PMSystem ob1=new PMSystem();
                    ob1.setName("xyz");
                    ob1.start();
                    try
                    {
                        ob1.join();
                    }catch (Exception e){}
                    PMSystem ob2=new PMSystem();
                    ob2.setName("abc");
                    ob2.start();
                    try
                    {
                        ob2.join();
                    }catch (Exception e){}
                    break;
                case 3:
                    System.out.print("Enter name of medicine you want to add:");
                    String medName=sc.next();
                    System.out.print("Enter price of medicine:");
                    double p=sc.nextDouble();
                    System.out.print("Enter quantity of medicine:");
                    int q=sc.nextInt();
                    pharmacy.addMedicine(new Medicine(medName,p,q));
                    break;
                case 4:
                    System.out.print("Enter the name of medicine you want to remove:");
                    String medName1=sc.next();
                    pharmacy.removeMedicine(medName1);
                    break;
                case 5:
                    System.out.print("Enter the name of medicine you want to update:");
                    String medName2=sc.next();
                    pharmacy.updateMedicine(medName2);
                    break;
                case 6:
                    System.out.println("Exiting!");
                    break;
                default:
                    System.out.println("Enter a valid choice");
            }
        }while(x!=6);
    }
    public void run()
    {
        synchronized(this)
        {
            System.out.println(Thread.currentThread().getName());
            System.out.println();
            Iterator i=pharmacy.med.iterator();
            while(i.hasNext())
            {
                System.out.println(i.next());
            }
            System.out.print("Enter the name of medicine:");
            String medName=sc.next();
            System.out.print("Enter Quantity of medicine:");
            int q=sc.nextInt();
            pharmacy.sellMedicine(medName,q);
        }
    }
}
