public class IPE_15
{
    //circular LinkedList
    //Q15,16,17,18,20,21,22
    class node
    {
        int data;
        node next;
        node(int data)
        {
            this.data=data;
            next=null;
        }
    }
    node first=null;

    void insertAtFisrt(int x)
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
            temp.next=n;
            first=n;
        }
    }

    void insertAtLast(int x)
    {
        node n=new node(x);
        if(first==null)
        {
            first=n;
            first.next=n;
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

    void insertBeforeValue(int num,int data)
    {
        if(first==null)
        {
            System.out.println("List is empty!");
        }
        boolean flag=false;
        node temp=first;
        do {
            if(temp.data==num)
            {
                flag=true;
                break;
            }
            temp=temp.next;
        }while(temp!=first);
        if(flag==false)
        {
            System.out.println("No such value found!");
        }
        else
        {
            if(first.data==num)
            {
                insertAtFisrt(data);
            }
            else
            {
                node n=new node(data);
                node temp1=first;
                while(temp1.next.data!=num)
                {
                    temp1=temp1.next;
                }
                n.next=temp1.next;
                temp1.next=n;
            }
        }
    }

    void insertAfterValue(int num,int data)
    {
        if(first==null)
        {
            System.out.println("List is empty!");
        }
        else
        {
            boolean flag=false;
            node temp=first;
            do {
                if(temp.data==num)
                {
                    flag=true;
                    break;
                }
                temp=temp.next;
            }while(temp!=first);
            if(flag==false)
            {
                System.out.println("No such value found!");
            }
            else
            {
                node n=new node(data);
                node temp1=first;
                while(temp1.data!=num)
                {
                    temp1=temp1.next;
                }
                n.next=temp1.next;
                temp1.next=n;
            }
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
            if(first.next==first)
            {
                first.next=null;
                first=null;
            }
            else
            {
                node temp=first;
                while(temp.next!=first)
                {
                    temp=temp.next;
                }
                node del=first;
                temp.next=del.next;
                first=del.next;
                del.next=null;
                del=null;
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
            if(first.next==first)
            {
                first.next=null;
                first=null;
            }
            else
            {
                node temp=first;
                while(temp.next.next!=first)
                {
                    temp=temp.next;
                }
                node del=temp.next;
                temp.next=first;
                del.next=null;
                del=null;
            }
        }
    }

    void deleteValue(int num)
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            boolean flag=false;
            node temp=first;
            do {
                if(temp.data==num)
                {
                    flag=true;
                    break;
                }
                temp=temp.next;
            }while(temp!=first);

            if(flag==false)
            {
                System.out.println("No such value found!");
            }
            else
            {
                if(first.data==num)
                {
                    deleteAtFirst();
                }
                else
                {
                    node temp1=first;
                    while(temp1.next.data!=num)
                    {
                        temp1=temp1.next;
                    }
                    node del=temp1.next;
                    temp1.next=del.next;
                    del.next=null;
                    del=null;
                }
            }
        }
    }
}
