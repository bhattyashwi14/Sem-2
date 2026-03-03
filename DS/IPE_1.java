public class IPE_1
{
    //stack
    int [] a;
    int top,cap;
    IPE_1(int size)
    {
        cap=size;
        a=new int[cap];
        top=-1;
    }

    void push(int x)
    {
        if(top>=cap-1)
        {
            System.out.println("Overflow");
        }
        else
        {
            top++;
            a[top]=x;
        }
    }

    void display()
    {
        if(top==-1)
        {
            System.out.println("Underflow");
        }
        else
        {
            for(int i=top;i>=0;i--)
            {
                System.out.println(a[i]);
            }

        }
    }

    void peep(int i)
    {
        if(top-i+1<0)
        {
            System.out.println("UnderFlow");
        }
        else
        {
            System.out.println(a[top-i+1]);
        }
    }

    int pop()
    {
        if(top==-1)
        {
            System.out.println("UnderFlow");
            return -1;
        }
        else
        {
            int z=a[top];
            top--;
            return z;
        }
    }

    void change(int i,int x)
    {
        if(top-i+1<0)
        {
            System.out.println("UnderFlow");
        }
        else
        {
            a[top-i+1]=x;
        }
    }
}
