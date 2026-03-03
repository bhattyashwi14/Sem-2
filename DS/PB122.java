public class PB122
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
        if (first == null)
        {
            System.out.println("The list is empty");
            return null;
        }
        else if (first.next == null)
        {
            node del = first;
            first = null;
            return del;
        }
        else
        {
            node del = first;
            node temp = first;
            while (temp.next.next != null)
            {
                temp = temp.next;
            }
            del = temp.next;
            temp.next = null;
            return del;
        }
    }
    node search(int val)
    {
        node dummy=first;
        boolean b=false;
        while(dummy!=null)
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
            node temp=first;
            node x=null;
            while(temp.next.data!=val)
            {
                temp=temp.next;
            }
            x=temp.next;
            return x;
        }
    }
    //PB123
    void displayOdd()
    {
        if(first==null)
        {
            System.out.println("The list is empty");
        }
        node extra=first;
        node temp=first;
        System.out.println(first.data);
        while(temp.next!=null && temp.next.next!=null)
        {
            temp=temp.next.next;
            System.out.println(temp.data);
        }
    }
    //PB124
    /*void displayFromEnd()
    {
        node temp=first;
        while(temp!=null)
        {
            insertAtLast(deleteAtLast().data);
        }
    }*/

    //PB130
    boolean unique()
    {
        int count=0;
        int count1=0;
        node dummy=first;
        node temp=first.next;
        if(first==null || first.next==null)
        {
            return true;
        }
        else
        {
            while(dummy!=null)
            {
                while(temp!=null)
                {
                    if(temp.data==dummy.data)
                    {
                        count++;
                    }
                    temp=temp.next;
                }
                dummy=dummy.next;
                temp=first;
                count1++;
            }
            if(count>count1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }
    public static void main(String[] args)
    {
        PB122 ob=new PB122();
        ob.insertAtFirst(1);
        ob.insertAtFirst(2);
        ob.insertAtFirst(3);
        ob.insertAtLast(4);
        ob.insertAtLast(5);
        ob.display();
        System.out.println(ob.search(7));
        System.out.println(ob.search(1).data);
        System.out.println();
        ob.displayOdd();
        System.out.println();
        System.out.println(ob.unique());
        ob.insertAtLast(5);
        System.out.println(ob.unique());
        //ob.displayFromEnd();
    }
}
