public class IPE_7_8
{
    //Q7,8,9,10,12,13,14
    //Q31,32,33,34,35
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

    void insertAtFirst(int x)
    {
        node n=new node(x);
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
            while(temp.next!=null)
            {
                temp=temp.next;
            }
            temp.next=n;
        }
    }

    void insertBeforeValue(int num,int data)
    {
        boolean flag=false;
        node temp=first;
        while(temp!=null)
        {
            if(temp.data==num)
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
            node n=new node(data);
            if(first.data==num)
            {
                insertAtFirst(data);
            }
            else
            {
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

    void insertAfterValue(int num, int data)
    {
        boolean flag=false;
        node temp=first;
        while(temp!=null)
        {
            if(temp.data==num)
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

    node deleteAtFirst()
    {
        if(first==null)
        {
            System.out.println("List is empty!");
            return null;
        }
        else
        {
            node temp=first;
            first=temp.next;
            temp.next=null;
            return temp;
        }
    }

    node deleteAtLast()
    {
        if(first==null)
        {
            System.out.println("List is empty!");
            return null;
        }
        else
        {
            if(first.next==null)
            {
               node del=first;
                first=null;
                return del;
            }
            node temp=first;
            while(temp.next.next!=null)
            {
                temp=temp.next;
            }
            node del=temp.next;
            temp.next=null;
            return del;
        }
    }

    node deleteValue(int x)
    {
        boolean flag=false;
        node temp=first;
        while(temp!=null)
        {
            if(temp.data==x)
            {
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag==false)
        {
            System.out.println("No such value found!");
            return null;
        }
        else
        {
            if(first.data==x)
            {
                return deleteAtFirst();
            }
            else
            {
                node temp1=first;
                while(temp1.next.data!=x)
                {
                    temp1=temp1.next;
                }
                node del=temp1.next;
                temp1.next=temp1.next.next;
                del.next=null;
                return del;
            }
        }
    }
    void deleteDuplicate()
    {
        if(first==null)
        {
            System.out.println("List is empty!");
        }
        else
        {
            node temp=first;
            while(temp!=null)
            {
                node cur=temp;
                while(cur.next!=null)
                {
                    if(cur.next.data==temp.data)
                    {
                        cur.next=cur.next.next;
                    }
                    else
                    {
                        cur=cur.next;
                    }
                }
                temp=temp.next;
            }
        }
    }
    void deleteEvenValues()
    {
        if(first==null)
        {
            System.out.println("List is empty!");
        }
        else
        {
            node temp=first;
            while(temp!=null)
            {
                if(temp.data%2==0)
                {
                    deleteValue(temp.data);
                    temp=first;
                }
                else
                {
                    temp=temp.next;
                }
            }
        }
    }

    void deleteOddValues()
    {
        if(first==null)
        {
            System.out.println("List is empty!");
        }
        else
        {
            node temp=first;
            while(temp!=null)
            {
                if(temp.data%2!=0)
                {
                    deleteValue(temp.data);
                    temp=first;
                }
                else
                {
                    temp=temp.next;
                }
            }
        }
    }

    int countNodes()
    {
        node temp=first;
        int count=0;
        while(temp!=null)
        {
            count++;
            temp=temp.next;
        }
        return count;
    }

    void deleteEvenNodes(IPE_7_8 ob)
    {
        node temp=first;
        IPE_7_8 ob1=new IPE_7_8();
        for(int i=1;i<=countNodes();i++)
        {
            if(i%2!=0)
            {
                ob1.insertAtLast(temp.data);
            }
            temp=temp.next;
        }
        ob.first=ob1.first;
    }

    void deleteOddNodes(IPE_7_8 ob)
    {
        node temp=first;
        IPE_7_8 ob1=new IPE_7_8();
        for(int i=1;i<=countNodes();i++)
        {
            if(i%2==0)
            {
                ob1.insertAtLast(temp.data);
            }
            temp=temp.next;
        }
        ob.first=ob1.first;
    }
}
