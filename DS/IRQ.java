public class IRQ
{
    int cap,front,rear;
    int [] Q;
    IRQ(int size)
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
    int deleteAtRear()
    {
        if(front==-1)
        {
            System.out.println("The queue is empty");
            return 0;
        }
        else
        {
            int y=Q[rear];
            if(front==rear)
            {
                front=-1;
                rear=-1;
                return 0;
            }
            else if(rear==0)
            {
                rear=cap-1;
            }
            else
            {
                rear=rear-1;
            }
            return y;
        }
    }
}
