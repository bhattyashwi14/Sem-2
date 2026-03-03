public class CLL_ExtraMethods
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
            while(temp.next!=null)
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
    void midPoint()
    {
        if(first==null)
        {
            System.out.println("The list is empty");
        }
        else
        {
            if(first.next==first)
            {
                System.out.println("Mid Value="+first.data);
            }
            else
            {
                node fast=first;
                node slow=first;
                while(fast.next!=first && fast.next.next!=first)
                {
                    fast=fast.next.next;
                    slow=slow.next;
                }
                System.out.println("Mid Value="+slow);
            }
        }
    }
    void insertBeforeParticularValue(int beforeValue,int value)
    {
        node n=new node(value);
        node temp=first;
        boolean flag=false;
       do
       {
           if(temp.data==beforeValue)
           {
               flag=true;
               break;
           }
           temp=temp.next;
       }while(temp!=first);
       if(flag==false)
       {
           System.out.println("No such value found");
       }
       else
       {
           if(first.next==first)
           {
               insertAtFirst(value);
           }
           else
           {
               node temp1=first;
               while (temp1.next.data!=beforeValue)
               {
                   temp1=temp1.next;
               }
               n.next=temp1.next;
               temp1.next=n;
           }
       }
    }
    void insertAfterParticularValue(int afterValue,int value)
    {
        node n=new node(value);
        node temp=first;
        boolean flag=false;
        do
        {
            if(temp.data==afterValue)
            {
                flag=true;
                break;
            }
            temp=temp.next;
        }while(temp!=first);
        if(flag==false)
        {
            System.out.println("No such value found");
        }
        else
        {
            node temp1=first;
            while(temp1.data!=afterValue)
            {
                temp1=temp1.next;
            }
            n.next=temp1.next;
            temp1.next=n;
        }
    }
    void deleteParticularValue(int value)
    {
        if(first==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            node temp=first;
            boolean flag=false;
            do
            {
                if(temp.data==value)
                {
                    flag=true;
                    break;
                }
                temp=temp.next;
            }while(temp!=first);
            if(flag==false)
            {
                System.out.println("No such value found");
            }
            else
            {
                if(first.data==value)
                {
                    deleteAtFirst();
                }
                else
                {
                    node temp1=first;
                    while(temp1.next.data!=value)
                    {
                        temp1=temp1.next;
                    }
                    node del=temp.next;
                    temp1.next=temp.next.next;
                    del.next=null;
                }
            }
        }
    }
}
