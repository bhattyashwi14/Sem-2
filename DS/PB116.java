public class PB116
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
    void countNodes()
    {
        int count=0;
        node temp=first;
        while(temp!=null)
        {
            count++;
            temp=temp.next;
        }
        System.out.println("Total number of nodes in the list="+count);
    }

    public static void main(String[] args)
    {
        PB116 ob=new PB116();
        ob.insertAtFirst(1);
        ob.insertAtFirst(2);
        ob.insertAtFirst(3);
        ob.insertAtLast(4);
        ob.insertAtLast(5);
        ob.display();
        ob.countNodes();
    }
}
