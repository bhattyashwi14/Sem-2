public class CircularLinkedList
{
    class node
    {
        int data;
        node next;
        node(int data)
        {
            this.data=data;
            this.next=next;
        }
    }
    node first=null;
    void insertAtFirst(int x)
    {
        node n=new node(x);
        if(first==null)
        {
            first=n;
            first.next=first;
        }
        else
        {
            node temp=first;
            while(temp.next!=first)
            {
                temp=temp.next;
            }
            n.next=first;
            first=n;
            temp.next=first;
        }
    }
    void insertAtLast(int x)
    {
        node n=new node(x);
        if(first==null)
        {
            first=n;
            first.next=first;
        }
        else
        {
            node temp=first;
            while(temp.next!=first)
            {
                temp=temp.next;
            }
            temp.next=n;
            n.next=first;
        }
    }
    void display()
    {
        if(first==null)
        {
            System.out.println("The list is empty");
        }
        else
        {
            if(first.next==first)
            {
                System.out.println(first.data);
            }
            node temp=first;
            while(temp.next!=first)
            {
                System.out.println(temp.data);
                temp=temp.next;
            }
            System.out.println(temp.data);
        }
    }
    void deleteAtLast()
    {
        if(first==null)
        {
            System.out.println("The list is empty");
        }
        else
        {
            node temp=first;
            while(temp.next.next!=first)
            {
                temp=temp.next;
            }
            temp.next.next=null;
            temp.next=first;
        }
    }
    void deleteAtFirst()
    {
        if(first==null)
        {
            System.out.println("The list is empty");
        }
        else
        {
            if(first.next==first)
            {
                first.next=null;
                first=null;
            }
            else
            {
                node temp=first;
                node del=first;
                while (temp.next!=first)
                {
                    temp=temp.next;
                }
                temp.next=first.next;
                first=first.next;
                del.next=null;
            }
        }
    }

    public static void main(String[] args)
    {
            CircularLinkedList c=new CircularLinkedList();
            c.insertAtLast(1);
            c.insertAtLast(2);
            c.insertAtLast(3);
            c.deleteAtLast();
            c.display();
    }
}
