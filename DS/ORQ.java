public class ORQ
{
    int cap,front,rear;
    int [] Q;
    ORQ(int size)
    {
        cap=size;
        front=-1;
        rear=-1;
        Q=new int [cap];
    }
    void insertAtRear(int y)
    {
        if((front==rear+1) || (front==0 && rear==cap-1))
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
    int deleteAtFront()
    {
        if(front==-1)
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
    void insertAtFront(int y)
    {
        if((front==rear+1) || (front==0 && rear==cap-1))
        {
            System.out.println("Overflow");
        }
        else
        {
            if(front==-1)
            {
                front=0;
                rear=0;
                Q[front]=y;
            }
            else if(front==0)
            {
                front=cap-1;
                Q[front]=y;
            }
            else
            {
                front=front-1;
                Q[front]=y;
            }
        }
    }
}
