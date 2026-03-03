public class IPE_6
{
    int a[];
    int top,cap;
    IPE_6(int size)
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

    int preFix(String exp)
    {
        for(int i=exp.length()-1;i>=0;i--)
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
                int val1=pop();
                int val2=pop();
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
