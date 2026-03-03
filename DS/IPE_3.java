public class IPE_3
{
    //Deque
    int front,rear,cap;
    int Q[];
    IPE_3(int size)
    {
        cap=size;
        front=-1;
        rear=-1;
        Q=new int[cap];
    }

    void insertAtFront(int x)
    {
        if(front==rear+1 || (front==0 && rear==cap-1))
        {
            System.out.println("OverFlow");
        }
        else
        {
            if(front==-1)
            {
                front=0;
                rear=0;
                Q[front]=x;
            }
            else if(front==0)
            {
                front=cap-1;
                Q[front]=x;
            }
            else
            {
                front=front-1;
                Q[front]=x;
            }
        }
    }

    void display()
    {
        if(front==-1)
        {
            System.out.println("The queue is empty");
        }
        else
        {
            int i=front;
            do {
                System.out.println(Q[i]);
                i=(i+1)%cap;
            }while(i!=(rear+1)%cap);
        }
    }

    int deleteAtRear()
    {
        if(front==-1)
        {
            System.out.println("The queue is empty");
            return -1;
        }
        else
        {
            int z=Q[rear];
            if(front==rear)
            {
                front=-1;
                rear=-1;
            }
            else if(rear==0)
            {
                rear=cap-1;
            }
            else
            {
                rear=rear-1;
            }
            return z;
        }
    }

    int getFront()
    {
        if(front==-1)
        {
            System.out.println("UnderFlow");
            return -1;
        }
        else
        {
            return Q[front];
        }
    }

    int getRear()
    {
        if(front==-1)
        {
            System.out.println("UnderFlow");
            return -1;
        }
        else
        {
            return Q[rear];
        }
    }
}
