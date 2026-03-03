public class OrderlyLinkedList
{
    //PB24
    class node
    {
        int data;
        node next;
        node prev;
        node(int data)
        {
            this.data=data;
            this.next=next;
            this.prev=prev;
        }
    }
    node first=null;
    void addOrdered(int x)
    {
        node n=new node(x);
        if(first==null)
        {
            first=n;
        }
        else
        {
            if(x<first.data)
            {
                first.prev=n;
                n.next=first;
                first=n;
            }
            else
            {
                node temp=first;
                while(x>temp.data && temp.next!=null)
                {
                    temp=temp.next;
                }
                if(temp.next==null)
                {
                    n.prev=temp;
                    temp.next=n;
                }
                else
                {
                    n.next=temp;
                    n.prev=temp.prev;
                    temp.prev.next=n;
                    temp.prev=n;
                }
            }
        }
    }
    void display()
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            while(temp!=null)
            {
                System.out.println(temp.data);
                temp=temp.next;
            }
        }
    }
    void displayReverse()
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            while(temp.next!=null)
            {
                temp=temp.next;
            }
            System.out.println("Reversed List:");
            while(temp!=null)
            {
                System.out.println(temp.data);
                temp=temp.prev;
            }
        }
    }

    public static void main(String[] args)
    {
        OrderlyLinkedList ob=new OrderlyLinkedList();
        ob.addOrdered(5);
        ob.addOrdered(7);
        ob.addOrdered(1);
        ob.addOrdered(9);
        ob.addOrdered(3);
        ob.display(); ob.displayReverse();
    }
}
