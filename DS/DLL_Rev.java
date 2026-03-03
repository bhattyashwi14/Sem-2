public class DLL_Rev
{
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
    void insertAtFirst(int x)
    {
        node n=new node(x);
        if(first==null)
        {
            first=n;
        }
        else
        {
            first.prev=n;
            n.next=first;
            first=n;
        }
    }
    void insertAtLast(int x)
    {
        node n=new node(x);
        if(first==null)
        {
            first=n;
        }
        else
        {
            node temp=first;
            while (temp.next!=null)
            {
                temp=temp.next;
            }
            n.prev=temp;
            temp.next=n;
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
    void deleteAtLast()
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            while(temp.next.next!=null)
            {
                temp=temp.next;
            }
           node del=temp.next;
            temp.next=null;
            del.prev=null;
            del=null;
        }
    }
    void deleteAtFirst()
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node del=first;
            first.next.prev=null;
            first=first.next;
            del.next=null;
            del=null;
        }
    }
    void insertBeforeParticularValue(int beforeValue, int val)
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            boolean flag=false;
            while(temp!=null)
            {
                if(temp.data==beforeValue)
                {
                    flag=true;
                    break;
                }
                temp=temp.next;
            }
            if(flag==false)
            {
                System.out.println("No such value found");
            }
            else
            {
                if(first.data==beforeValue)
                {
                    insertAtFirst(val);
                }
                else
                {
                    node n=new node(val);
                    node temp1=first;
                    while (temp1.next.data!=beforeValue)
                    {
                        temp1=temp1.next;
                    }
                    n.prev=temp1;
                    n.next=temp1.next;
                    temp1.next.prev=n;
                    temp1.next=n;
                }
            }
        }
    }
    void insertAfterParticularValue(int aftervalue, int val)
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            boolean flag=false;
            while(temp!=null)
            {
                if(temp.data==aftervalue)
                {
                    flag=true;
                    break;
                }
                temp=temp.next;
            }
            if(flag==false)
            {
                System.out.println("No such value found");
            }
            else
            {
                if(first.data==aftervalue)
                {
                    insertAtFirst(val);
                }
                node n=new node(val);
                node temp1=first;
                while(temp1.data!=aftervalue)
                {
                    temp1=temp1.next;
                }
                if(temp1.next==null)
                {
                    insertAtLast(val);
                }
                else
                {
                    n.prev=temp1;
                    n.next=temp1.next;
                    temp1.next.prev=n;
                    temp1.next=n;
                }
            }
        }
    }
    void deleteParticularValue(int val)
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            boolean flag=false;
            while(temp!=null)
            {
                if(temp.data==val)
                {
                    flag=true;
                    break;
                }
                temp=temp.next;
            }
            if(flag==false)
            {
                System.out.println("No such value found!");
            }
            else
            {
                if(first.data==val)
                {
                    deleteAtFirst();
                }
                else
                {
                    node temp1=first;
                    while(temp1.next.data!=val)
                    {
                        temp1=temp1.next;
                    }
                    if(temp1.next==null)
                    {
                        deleteAtLast();
                    }
                    else
                    {
                        node del=temp1.next;
                        del.next.prev=temp1;
                        temp1.next=del.next;
                        del.next=null;
                        del.prev=null;
                        del=null;
                    }
                }
            }
        }
    }
    void displayOddValues() //PB31
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            System.out.println("Odd values of the list:");
            while(temp!=null)
            {
                if(temp.data%2!=0)
                {
                    System.out.println(temp.data);
                }
                temp=temp.next;
            }
        }
    }
    void max() //PB28
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            int max=first.data;
            while(temp!=null)
            {
                if(temp.data>max)
                {
                    max=temp.data;
                }
                temp=temp.next;
            }
            System.out.println("Maximum value:"+max);
        }
    }
    void midPoint() //PB21
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node fast=first;
            node slow=first;
            while(fast.next!=null && fast.next.next!=null)
            {
                fast=fast.next.next;
                slow=slow.next;
            }
            System.out.println("Mid Point:"+slow.data);
        }
    }

    public static void main(String[] args)
    {
        DLL_Rev ob=new DLL_Rev();
        ob.insertAtFirst(1);
        ob.insertAtFirst(2);
        ob.insertAtFirst(3);
        ob.insertAtLast(5);
        ob.insertAtLast(7);
        ob.insertAtLast(8);
        ob.display();
        ob.displayReverse();
        System.out.println();
        ob.deleteAtFirst();
        ob.deleteAtLast();
        ob.display();ob.displayReverse();
        System.out.println();
        ob.insertBeforeParticularValue(5,4);
        ob.display(); ob.displayReverse();
        System.out.println();
        ob.insertAfterParticularValue(7,9);
        ob.display(); ob.displayReverse();
        System.out.println();
        ob.deleteParticularValue(4);
        ob.display(); ob.displayReverse();
        System.out.println();
        ob.displayOddValues();
        System.out.println();
        ob.max();
        ob.midPoint();
    }
}
