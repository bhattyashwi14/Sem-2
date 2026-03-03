public class LinkedList
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
    void insertAtFront(int y)
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
    void display()
    {
        if(first==null)
        {
            System.out.println("List is empty");
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
            node temp=first;
            node del=first;
            while(temp.next.next!=null)
            {
                temp=temp.next;
            }
            del=temp.next;
            temp.next=null;
            return del;
        }
    }
}
