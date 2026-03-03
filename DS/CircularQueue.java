public class CircularQueue
{
    int front,rear,cap;
    int [] Q;
    CircularQueue(int size)
    {
        cap=size;
        front=-1;
        rear=-1;
        Q=new int[cap];
    }
    void enqueue(int y)
    {
        if((front==rear+1)||(front==0 && rear==cap-1))
        {
            System.out.println("Overflow");
        }
        else
        {
           rear=(rear+1)%cap;
           Q[rear]=y;
           if(front==-1)
           {
               front=0;
           }
        }
    }
    int dequeue()
    {
        if(front<0)
        {
            System.out.println("The queue is empty");
            return 0;
        }
        else
        {
            int y=Q[front];
            if(front==rear)
            {
                front=-1;
                rear=-1;
            }
            else
            {
                front=(front+1)%cap;
            }
            return y;
        }
    }
    void display()
    {
        if(front==-1)
        {
            System.out.println("Underflow");
        }
        else
        {
            int i=front;
            do{
                System.out.println(Q[i]);
                i=(i+1)%cap;
            }while(i!=(rear+1)%cap);
        }
    }

    public static void main(String[] args)
    {
        CircularQueue ob=new CircularQueue(3);
        ob.enqueue(1);
        ob.enqueue(2);
        ob.enqueue(3);
        ob.display();
        System.out.println();
        System.out.println(ob.dequeue());
        ob.enqueue(14);
        System.out.println();
        ob.display();
    }
}
