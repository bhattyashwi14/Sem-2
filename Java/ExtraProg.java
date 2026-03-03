public class ExtraProg
{
    int [] Q;
    int cap,front,rear;
    ExtraProg(int size)
    {
        cap=size;
        Q=new int [cap];
        front=-1;
        rear=-1;
    }
    void enqueue(int y)
    {
        if(rear>=cap-1)
        {
            System.out.println("Overflow");
        }
        else
        {
            rear++;
            Q[rear]=y;
        }
        if(front==-1)
        {
            front=0;
        }
    }
    void display()
    {
        if(front<=-1)
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
    int dequeue()
    {
        if(front<=-1)
        {
            System.out.println("Underflow");
            return 0;
        }
        else
        {
            int x=Q[front];
            if(front==rear)
            {
                front=-1;
                rear=-1;
            }
            else
            {
                front++;
            }
            return x;
        }
    }
    void push(int y)
    {
        if(rear>=cap)
        {
            System.out.println("Overflow");
        }
        else
        {
            enqueue(y);
        }
    }
    int pop()
    {
        ExtraProg Q1=new ExtraProg(cap);
        if(front<=-1)
        {
            System.out.println("Underflow");
            return 0;
        }
        else
        {
            int x=Q[rear];
            if(front==rear)
            {
                front=-1;
                rear=-1;
            }
            else
            {
                Q1.front=front;
                Q1.rear=rear;
                for (int i=front;i<rear;i++)
                {
                    Q1.enqueue(dequeue());
                }
                Q = Q1.Q;
                Q1.display();
                rear--;
            }
            return x;
        }
    }
}
class RunQ
{
    public static void main(String[] args)
    {
        ExtraProg ob=new ExtraProg(7);
        ob.push(18);
        ob.push(14);
        //ob.display();
        ob.pop();
        //System.out.println();
        ob.display();
    }
}