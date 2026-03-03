public class SimpleQueue
{
    int cap,front,rear;
    int [] Q;
    SimpleQueue(int size)
    {
        cap=size;
        Q=new int [cap];
        front=-1;
        rear=-1;
    }
    void enqueue(int y)
    {
        if(rear>cap-1)
        {
            System.out.println("Overflow");
        }
        else
        {
            rear++;
            Q[rear]=y;
        }
        if(front<0)
        {
            front=front+1;
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
            int y = Q[front];
            if (front == rear)
            {
                front = -1;
                rear = -1;
            }
            else
            {
                front = front + 1;
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
            for(int i=front;i<=rear;i++)
            {
                System.out.println(Q[i]);
            }
        }
    }

    public static void main(String[] args)
    {
        SimpleQueue ob=new SimpleQueue(5);
        ob.enqueue(1);
        ob.enqueue(2);
        ob.enqueue(3);
        ob.enqueue(4);
        ob.enqueue(5);
        ob.display();
        System.out.println();
        System.out.println(ob.dequeue());
        System.out.println();
        //ob.enqueue(14);
        ob.display();
    }
}
