public class IPE_2
{
    //queue
    int cap,front,rear;
    int [] a;
    IPE_2(int size)
    {
        cap=size;
        front=-1;
        rear=-1;
        a=new int[cap];
    }

    void enqueue(int x)
    {
        if(rear>=cap-1)
        {
            System.out.println("Overflow");
        }
        else
        {
            rear++;
            a[rear]=x;
        }
        if(front==-1)
        {
            front=0;
        }

    }

    int dequeue()
    {
        if(front==-1)
        {
            System.out.println("UnderFlow");
            return 0;
        }
        else
        {
            int z=a[front];
           if(front==rear)
           {
               front=-1;
               rear=-1;
           }
           else
           {
               front++;
           }
            return z;
        }
    }

    void display()
    {
        if(front==-1)
        {
            System.out.println("UnderFlow");
        }
        else
        {
            for(int i=front;i<=rear;i++)
            {
                System.out.println(a[i]);
            }
        }
    }

    int getFront()
    {
        if(front==-1)
        {
            System.out.println("The queue is empty");
            return -1;
        }
        else
        {
            return a[front];
        }
    }

    int getRear()
    {
        if(rear==-1)
        {
            System.out.println("The queue is empty");
            return -1;
        }
        else
        {
            return a[rear];
        }
    }
}
