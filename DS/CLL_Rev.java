public class CLL_Rev
{
    class node
    {
        int data;
        node next;

        node(int data)
        {
            this.data = data;
            this.next = next;
        }
    }

    node first = null;

    void insertAtFirst(int x)
    {
        node n = new node(x);
        if (first == null)
        {
            first = n;
            first.next = first;
        }
        else
        {
            node temp = first;
            while (temp.next != first)
            {
                temp = temp.next;
            }
            n.next = first;
            first = n;
            temp.next = first;
        }
    }

    void insertAtLast(int x)
    {
        node n = new node(x);
        if (first == null)
        {
            first = n;
            first.next = first;
        }
        else
        {
            node temp = first;
            while (temp.next != first)
            {
                temp = temp.next;
            }
            temp.next = n;
            n.next = first;
        }
    }

    void display()
    {
        if (first == null)
        {
            System.out.println("The List is empty!");
        }
        else
        {
            if (first.next == first)
            {
                System.out.println(first.data);
            }
            else
            {
                node temp = first;
                while (temp.next != first)
                {
                    System.out.println(temp.data);
                    temp = temp.next;
                }
                System.out.println(temp.data);
            }
        }
    }

    void deleteAtLast()
    {
        if (first == null)
        {
            System.out.println("The List is empty!");
        }
        else
        {
            node temp = first;
            while (temp.next.next != first)
            {
                temp = temp.next;
            }
            temp.next.next = null;
            temp.next = first;
        }
    }

    void deleteAtFirst()
    {
        if (first == null)
        {
            System.out.println("The List is empty!");
        }
        else
        {
            if (first.next == first)
            {
                first.next = null;
                first = null;
            }
            else
            {
                node temp = first;
                node del = first;
                while (temp.next != first)
                {
                    temp = temp.next;
                }
                temp.next = first.next;
                first = first.next;
                del.next = null;
                del = null;
            }
        }
    }

    void midPoint()
    {
        if (first == null)
        {
            System.out.println("The List is empty!");
        } else {
            if (first.next == first)
            {
                System.out.println(first.data);
            }
            else
            {
                node fast = first;
                node slow = first;
                while (fast.next != first && fast.next.next != first)
                {
                    fast = fast.next.next;
                    slow = slow.next;
                }
                System.out.println("Mid point:" + slow.data);
            }
        }
    }

    void insertBeforeParticularValue(int beforeValue, int val)
    {
        if (first == null)
        {
            System.out.println("The List is null!");
        }
        else
        {
            boolean flag = false;
            node temp = first;
            do {
                if (temp.data != beforeValue)
                {
                    flag = true;
                    break;
                }
                temp = temp.next;
            } while (temp != first);
            if (flag == false)
            {
                System.out.println("No such value found");
            }
            else
            {
                node n = new node(val);
                if (first.data == beforeValue)
                {
                    insertAtFirst(val);
                }
                else
                {
                    node temp1 = first;
                    while (temp1.next.data != beforeValue)
                    {
                        temp1 = temp1.next;
                    }
                    n.next = temp1.next;
                    temp1.next = n;
                }
            }
        }
    }

    void insertAfterValue(int afterValue, int val)
    {
        if (first == null)
        {
            System.out.println("The List is null!");
        }
        else
        {
            boolean flag = false;
            node temp = first;
            do {
                if (temp.data != afterValue)
                {
                    flag = true;
                    break;
                }
                temp = temp.next;
            } while (temp != first);
            if (flag == false)
            {
                System.out.println("No such value found");
            }
            else
            {
                node n = new node(val);
                if (first.data == afterValue)
                {
                    insertAtFirst(val);
                }
                else
                {
                    node temp1 = first;
                    while (temp1.data != afterValue)
                    {
                        temp1 = temp1.next;
                    }
                    n.next = temp1.next;
                    temp1.next = n;
                }
            }
        }
    }

    void deleteParticularValue(int val)
    {
        if (first == null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp = first;
            boolean flag = false;
            do {
                if (temp.data == val)
                {
                    flag = true;
                    break;
                }
                temp = temp.next;
            } while (temp != first);
            if (flag == false)
            {
                System.out.println("No such value found");
            }
            else
            {
                if (first.data == val)
                {
                    deleteAtFirst();
                }
                else
                {
                    node temp1 = first;
                    while (temp1.next.data != val)
                    {
                        temp1 = temp1.next;
                    }
                    node del = temp1.next;
                    temp1.next = del.next;
                    del.next = null;
                    del = null;
                }
            }
        }
    }

    void checkLL_CLL(CircularLinkedList ob)//PB32
    {
        node temp = first;
        while (temp.next != null && temp.next != first)
        {
            temp = temp.next;
        }
        if (temp.next == null)
        {
            System.out.println("It is Linear LinkedList");
        }
        if (first.next == first)
        {
            System.out.println("It is a Circular LinkedList");
        }
    }

    CLL_Rev add(CLL_Rev c1, CLL_Rev c2)//PB48
    {
        CLL_Rev c3 = new CLL_Rev();
        node temp1 = c1.first;
        node temp2 = c2.first.next;
        while (temp1.next != c1.first)
        {
            c3.insertAtLast(temp1.data + temp2.data);
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        c3.insertAtLast(c2.first.data + temp1.data);
        return c3;
    }

    void deleteParticularIndex(int i)//PB49
    {
        if (first == null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp = first;
            int count = 0;
            do {
                temp = temp.next;
                count++;
            } while (temp != first);
            if (i > count || i == 0)
            {
                System.out.println("Enter valid index value");
            }
            else
            {
                node temp1 = first;
                int c = 0;
                do {
                    c++;
                    if (c == i)
                    {
                        deleteParticularValue(temp1.data);
                        break;
                    }
                    temp1 = temp1.next;
                } while (temp1 != first);
            }
        }
        /*
         for(int i=1;i<index-1;i++)
         {
           temp1=temp1.next;
         }
           node del=temp.next;
           temp.next=del.next;
           del.next=null;
           del=null;
           */
    }

    void deleteOddPositions()//PB47
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            int count=0;
            do
            {
                count++;
                if(count%2!=0)
                {
                   deleteParticularValue(temp.data);
                }
               temp=temp.next;
            }while(temp!=first);
        }
    }
    void sum() //PB27
    {
        if(first==null)
        {
            System.out.println("The list is empty!");
        }
        else
        {
            node temp=first;
            int sum=0;
            do
            {
                sum=sum+temp.data;
                temp=temp.next;
            }while(temp!=first);
            System.out.println("Sum:"+sum);
        }
    }
    void displayOddPositions()
    {
        if(first==null)
        {
            System.out.println("the list is empty!");
        }
        else
        {
            node temp=first;
            int count=0;
            System.out.println("Odd positioned nodes:");
            do
            {
                count++;
                        if(count%2!=0)
                        {
                            System.out.println(temp.data);
                        }
                temp=temp.next;
            }while(temp!=first);
        }
    }

    public static void main(String[] args)
    {
        CLL_Rev ob=new CLL_Rev();
        CLL_Rev ob1=new CLL_Rev();
        CLL_Rev ob2=new CLL_Rev();
        /*ob.insertAtFirst(1);
        ob.insertAtFirst(2);
        ob.insertAtFirst(3);
        ob.insertAtLast(5);
        ob.insertAtLast(7);
        ob.insertAtLast(8);
        ob.display();
        System.out.println();
        ob.deleteAtFirst();
        ob.deleteAtLast();
        ob.display();
        ob.midPoint();
        ob.insertAtFirst(3);
        ob.display();
        ob.midPoint();
        ob.insertBeforeParticularValue(5,4);
        ob.display();
        System.out.println();
        ob.insertAfterValue(5,6);
        ob.display();
        System.out.println();
        ob.deleteParticularValue(6);
        ob.display();*/
        ob.insertAtLast(1);
        ob.insertAtLast(2);
        ob.insertAtLast(3);
        ob1.insertAtLast(1);
        ob1.insertAtLast(2);
        ob1.insertAtLast(3);
        ob2=ob2.add(ob,ob1);
        ob2.display();
        System.out.println();
        ob2.deleteParticularIndex(1);
        ob2.display();
        System.out.println();
        ob.insertAtLast(4);
        ob.insertAtLast(5);
        ob.display();
        System.out.println();
        //ob.deleteOddPositions();
        //ob.display();
        ob.sum();
        ob.displayOddPositions();
    }
}
