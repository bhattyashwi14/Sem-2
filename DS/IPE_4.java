public class IPE_4
{
    //cicular Queue
    int front,rear,cap;
    int Q[];
    IPE_4(int size)
    {
        cap=size;
        front=-1;
        rear=-1;
        Q=new int[cap];
    }

    void enqueue(int x)
    {
        if(front==rear+1 || (front==0 && rear==cap-1))
        {
            System.out.println("Overflow");
        }
        else
        {
            rear=(rear+1)%cap;
            Q[rear]=x;
            if(front==-1)
            {
                front=0;
            }
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
            int i=front;
            do {
                System.out.println(Q[i]);
                i=(i+1)%cap;
            }while(i!=(rear+1)%cap);
        }
    }

    int dequeue()
    {
        if(front==-1)
        {
            System.out.println("UnderFlow");
            return -1;
        }
        else
        {
            int z=Q[front];
            if(front==rear)
            {
                front=-1;
                rear=-1;
            }
            else
            {
                front=(front+1)%cap;
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
