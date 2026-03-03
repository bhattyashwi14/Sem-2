public class DoublyLinkedList
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
            n.next=first;          /*n.next=first; first=n; first.next.prev=n*/
            first.prev=n;
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
            n.prev=temp;
        }
    }
    void display()
    {
        if(first==null)
        {
            System.out.println("The List is empty");
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
            System.out.println("The List is empty");
        }
        else
        {
            node temp=first;
            while(temp.next!=null)
            {
                temp=temp.next;
            }
            System.out.println("Reversed List:");
            while(temp!=first.prev)
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
            System.out.println("The List is empty");
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
        }
    }
    void deleteAtFirst()
    {
        if(first==null)
        {
            System.out.println("The List is empty");
        }
        else
        {
            node del=first.next;
            first.next=null;
            del.prev=null;
            first=del;

            //OR
            /*node temp=first;
              first.next.prev=null;
              first=first.next;
              temp.next=null;
              temp.prev=null;
              temp=null;*/
        }
    }
    void insertBeforeParticularValue(int beforeValue,int value)
    {
        if(first==null)
        {
            System.out.println("The list is empty");
        }
        else
        {
            node temp=first;
            boolean flag=false;
            do {
                if(temp.data==beforeValue)
                {
                    flag=true;
                    break;
                }
                temp=temp.next;
            }while(temp!=null);
            if(flag==false)
            {
                System.out.println("No such value found");
            }
            else
            {
                node  n=new node(value);
                if(first.data==beforeValue)
                {
                    insertAtFirst(value);
                }
                node temp1=first;
                while(temp1.next.data!=beforeValue)
                {
                    temp1=temp1.next;
                }
                n.next=temp1.next;
                temp1.next.prev=n;
                temp1.next=n;
                n.prev=temp1;
            }
        }
    }
    void insertAfterParticularValue(int afterValue,int value)
    {
        if(first==null)
        {
            System.out.println("The List is empty");
        }
        else
        {
            node temp=first;
            boolean flag=false;
            do {
                if(temp.data==afterValue)
                {
                    flag=true;
                    break;
                }
                temp=temp.next;
            } while(temp!=null);
            if(flag==false)
            {
                System.out.println("No such value found");
            }
            else
            {
                node n=new node(value);
                node temp1= first;
                while(temp1.data!=afterValue)
                {
                    temp1=temp1.next;
                }
                if(temp1.next==null)
                {
                    insertAtLast(value);
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
    void deleteParticularValue(int value)
    {
        if(first==null)
        {
            System.out.println("The List is empty");
        }
        else
        {
            node temp=first;
            boolean flag=false;
            do {
                if(temp.data==value)
                {
                    flag=true;
                    break;
                }
                temp=temp.next;
            } while(temp!=null);
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
                node n=new node(value);
                node temp1= first;
                while(temp1.next.data!=value)
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
                    temp1.next=temp1.next.next;
                    del.next=null;
                    del.prev=null;
                    del=null;
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
          DoublyLinkedList d=new DoublyLinkedList();
          d.insertAtFirst(1);
          d.insertAtFirst(2);
          d.insertAtFirst(3);
          d.display();
          d.displayReverse();
          System.out.println();
          d.insertAtLast(5);
          d.insertAtLast(7);
          d.display();
          d.displayReverse();
          System.out.println();
          d.deleteAtFirst();
          d.display();
          d.displayReverse();
          System.out.println();
          d.deleteAtLast();
          d.display();
          d.displayReverse();
          System.out.println();
          d.insertBeforeParticularValue(5,7);
          d.display();
          d.displayReverse();
          System.out.println();
          d.insertAfterParticularValue(5,7);
          d.display();
          d.displayReverse();
    }
}
