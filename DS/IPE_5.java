public class IPE_5
{
    int a[];
    int top,cap;
    IPE_5(int size)
    {
        cap=size;
        top=-1;
        a=new int[cap];
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

    int pop()
    {
        if(top==-1)
        {
            System.out.println("Underflow");
            return -1;
        }
        else
        {
            int z=a[top];
            top--;
            return z;
        }
    }

    int postFix(String exp)
    {
        for(int i=0;i<exp.length();i++)
        {
            char ch=exp.charAt(i);
            if(ch==' ')
            {
                continue;
            }
            if(Character.isDigit(ch))
            {
                push(ch-'0');
            }
            else
            {
                int val2=pop();
                int val1=pop();
                switch (ch)
                {
                    case '+':
                        push(val1+val2);
                        break;
                    case '-':
                        push(val1-val2);
                        break;
                    case '*':
                        push(val1*val2);
                        break;
                    case '/':
                        push(val1/val2);
                        break;
                    case '^':
                        push((int)Math.pow(val1,val2));
                        break;
                }
            }
        }
        return pop();
    }
}
