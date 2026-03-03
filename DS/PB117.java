public class PB117
{
    class node
    {
        int data;
        node next;
        node(int data)
        {
            this.data=data;
            this.next=null;
        }
    }
    node first=null;
    void insertAtFirst(int y)
    {
        node n=new node(y);
        if(first==null)
        {
            first=n;
        }
        else
        {
            n.next=first;
            first=n;
        }
    }
    void insertAtLast(int y)
    {
        node n=new node(y);
        node temp=first;
        if(first==null)
        {
            first=n;
        }
        else
        {
            while(temp.next!=null)
            {
                temp=temp.next;
            }
            temp.next=n;
        }
    }
    void display()
    {
        node temp=first;
        if(first==null)
        {
            System.out.println("The list is Empty");
        }
        else
        {
            while(temp!=null)
            {
                System.out.println(temp.data);
                temp=temp.next;
            }
        }
    }
    node deleteAtFirst()
    {
        node del=first;
        if(first==null)
        {
            System.out.println("The list is empty");
            return null;
        }
        else
        {
            first.next=del;
            del.next=null;
            return del;
        }
    }
    node deleteAtLast()
    {
        if(first==null)
        {
            System.out.println("The list is empty");
            return null;
        }
        else if(first.next==null)
        {
            node del=first;
            first=null;
            return del;
        }
        else
        {
            node del=first;
            node temp=first;
            while(temp.next.next!=null)
            {
                temp=temp.next;
            }
            del=temp.next;
            temp.next=null;
            return del;
        }
    }
    node deleteValue(int val)
    {
        node dummy=first;
        boolean b=false;
        while (dummy!=null)
        {
            if(dummy.data==val)
            {
                b=true;
                break;
            }
            dummy=dummy.next;
        }
        if(b==false)
        {
            System.out.println("No such value found");
            return null;
        }
        else
        {
            node del=first;
            node temp=first;
            if(first.data==val)
            {
                del=deleteAtFirst();
                return del;
            }
            while(temp.next.data!=val)
            {
                temp=temp.next;
            }
            del=temp.next;
            temp.next=del.next;
            del.next=null;
            return del;
        }
    }
    public static void main(String[] args)
    {
        PB117 ob=new PB117();
        ob.insertAtFirst(1);
        ob.insertAtFirst(2);
        ob.insertAtFirst(3);
        ob.insertAtLast(4);
        ob.insertAtLast(5);
        ob.display();
        ob.deleteValue(7);
        System.out.println(ob.deleteValue(1).data);
        System.out.println();
        ob.display();
    }
}
